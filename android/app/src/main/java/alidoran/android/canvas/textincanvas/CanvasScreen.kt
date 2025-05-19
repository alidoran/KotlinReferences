package alidoran.android.canvas.textincanvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CanvasScreen(viewModel: CanvasViewModel = viewModel()) {
    var editingItemId by remember { mutableStateOf<String?>(null) }
    var editingText by remember { mutableStateOf("") }
    var editingColor by remember { mutableStateOf(Color.Black) }
    var editingFontSize by remember { mutableFloatStateOf(40f) }
    val dragOffset = remember { mutableStateOf(Offset.Zero) }
    var editingFontWeight by remember { mutableStateOf(FontWeightOption.Normal) }
    val textItems by remember { mutableStateOf(viewModel.textItems) }

    Box(Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(editingItemId) {
                    awaitEachGesture {
                        var down = awaitFirstDown()
                        var drag: PointerInputChange? = null
                        var zoom = 1f
                        var pan = Offset.Zero

                        val item = textItems.find { it.id == editingItemId }

                        do {
                            val event = awaitPointerEvent()
                            val changes = event.changes

                            if (changes.size == 1 && !changes[0].isConsumed) {
                                val change = changes[0]
                                if (change.pressed) {
                                    drag = change
                                    pan += change.positionChange()
                                    change.consume()
                                }
                            }

                            if (changes.size == 2) {
                                val zoomChange = event.calculateZoom()
                                val panChange = event.calculatePan()
                                zoom *= zoomChange
                                pan += panChange
                                changes.forEach { it.consume() }
                            }

                        } while (changes.any { it.pressed })

                        if (pan.getDistance() < 10f && zoom == 1f) {
                            // Treat as tap
                            val tapOffset = down.position
                            val tappedItem = viewModel.textItems.findLast { item ->
                                val paint = Paint().apply {
                                    textSize = item.fontSize
                                }
                                val textWidth = paint.measureText(item.text)
                                val left = item.x
                                val top = item.y - item.fontSize  // approx text height, use fontSize for vertical size
                                val right = left + textWidth
                                val bottom = item.y
                                tapOffset.x in left..right && tapOffset.y in top..bottom
                            }

                            if (tappedItem != null) {
                                editingItemId = tappedItem.id
                                editingText = tappedItem.text
                                editingFontSize = tappedItem.fontSize
                                editingColor = tappedItem.toColor()
                                editingFontWeight = tappedItem.fontWeight
                                dragOffset.value = Offset.Zero
                            } else {
                                val newId = viewModel.addText("", tapOffset)
                                editingItemId = newId
                                editingText = ""
                                editingFontSize = 40f
                                editingColor = Color.Black
                                dragOffset.value = Offset.Zero
                            }
                        }
                    }
                },

            onDraw = {
                textItems.forEach { item ->
                    if (item.id != editingItemId) {
                        drawContext.canvas.nativeCanvas.drawText(
                            item.text,
                            item.x,
                            item.y,
                            Paint().apply {
                                color = item.toColor().toArgb()
                                textSize = item.fontSize
                                isFakeBoldText = item.fontWeight == FontWeightOption.Bold
                            }
                        )
                    }
                }
            }
        )

        val item = textItems.find { it.id == editingItemId }
        val ascentPx = remember(editingFontSize) {
            Paint().apply {
                textSize = editingFontSize
            }.ascent()
        }

        item?.let {
            Column {
                val focusRequester = remember { FocusRequester() }
                var hasFocus by remember { mutableStateOf(false) }

                BasicTextField(
                    value = editingText,
                    onValueChange = {
                        editingText = it
                        viewModel.updateText(item.id, it)
                    },
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                item.x.toInt(),
                                (item.y + ascentPx.toInt()).toInt()
                            )
                        }
                        .widthIn(min = 100.dp, max = 300.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (hasFocus && !focusState.isFocused) {
                                viewModel.updatePosition(
                                    item.id,
                                    item.x + dragOffset.value.x,
                                    item.y + dragOffset.value.y
                                )
                                editingItemId = null
                            }
                            hasFocus = focusState.isFocused
                        }
                        .focusable(false)
                        .pointerInput(item.id) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                val item = textItems.find { it.id == editingItemId }
                                if (item != null) {
                                    val newX = item.x + pan.x
                                    val newY = item.y + pan.y
                                    viewModel.updatePosition(item.id, newX, newY)

                                    val newFontSize = (item.fontSize * zoom).coerceIn(10f, 150f)
                                    editingFontSize = newFontSize
                                    viewModel.updateFontSize(item.id, newFontSize)
                                }
                            }
                        },
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.updatePosition(
                                item.id,
                                item.x + dragOffset.value.x,
                                item.y + dragOffset.value.y
                            )
                            editingItemId = null
                        }
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = with(LocalDensity.current) { editingFontSize.toSp() },
                        color = editingColor,
                        fontWeight = if (editingFontWeight == FontWeightOption.Bold) FontWeight.Bold else FontWeight.Normal,
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            innerTextField()
                        }
                    }
                )

                Box(
                    modifier = Modifier,
                ) {
                    Row(
                        Modifier
                            .offset {
                                IntOffset(
                                    (item.x + dragOffset.value.x).toInt(),
                                    (item.y + ascentPx.toInt() + dragOffset.value.y).toInt()
                                )
                            }
                            .padding(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        val colors = listOf(Color.Black to "Black", Color.Red to "Red")
                        colors.forEach { (color, _) ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(30.dp)
                                    .background(color, shape = CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                                    .clickable {
                                        editingColor = color
                                        viewModel.updateColor(item.id, color)
                                    }
                            )
                        }
                    }

                    Row(
                        Modifier
                            .offset {
                                IntOffset(
                                    (item.x + dragOffset.value.x).toInt(),
                                    (item.y + 50 + dragOffset.value.y).toInt()
                                )
                            }
                            .padding(4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        val weights = listOf(
                            FontWeightOption.Normal to "Normal",
                            FontWeightOption.Bold to "Bold"
                        )
                        weights.forEach { (weight, label) ->
                            Button(
                                onClick = {
                                    editingFontWeight = weight
                                    viewModel.updateFontWeight(item.id, weight)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (editingFontWeight == weight) Color.Gray else Color.LightGray
                                ),
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text(label)
                            }
                        }
                    }
                }
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { viewModel.saveAsJson() }) { Text("Save") }
            Button(onClick = { viewModel.resetScreen() }) { Text("Reset") }
            Button(onClick = { viewModel.loadFromJson() }) { Text("Load") }
        }
    }
}


