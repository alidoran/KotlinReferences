package alidoran.android.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.roundToInt

data class CanvasText(
    val id: Int,
    var text: String,
    var position: Offset = Offset(100f, 100f),
    var fontSize: Float = 48f,
    var scale: Float = 1f,
    var isEditing: Boolean = false
) {
    fun bounds(): Rect {
        val width = text.length * fontSize * scale * 0.5f
        val height = fontSize * scale
        return Rect(position, Size(width, height))
    }
}

@Composable
fun InteractiveTextCanvas() {
    var items by remember { mutableStateOf(mutableListOf<CanvasText>()) }
    var selectedId by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. Canvas Layer - Draw texts
        Canvas(modifier = Modifier.fillMaxSize()) {
            items.forEach { item ->
                if (!item.isEditing) {
                    drawIntoCanvas { canvas ->
                        val paint = Paint().asFrameworkPaint().apply {
                            isAntiAlias = true
                            color = Color.Black.toArgb()
                            textSize = item.fontSize * item.scale
                        }
                        canvas.nativeCanvas.drawText(
                            item.text,
                            item.position.x,
                            item.position.y,
                            paint
                        )
                    }
                }
            }
        }

        // 2. Interaction Layer - Tap & transform
        Box(
            modifier = Modifier
                .matchParentSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = { offset ->
                            val newItem = CanvasText(
                                id = items.size + 1,
                                text = "New Text",
                                position = offset
                            )
                            items = items.toMutableList().apply { add(newItem) }
                            selectedId = newItem.id
                        },
                        onTap = { tap ->
                            selectedId = items.findLast { it.bounds().contains(tap) }?.id
                            items = items.map {
                                it.copy(isEditing = false)
                            }.toMutableList()
                        },
                        onDoubleTap = { tap ->
                            val tapped = items.findLast { it.bounds().contains(tap) }
                            tapped?.let { t ->
                                items = items.map {
                                    if (it.id == t.id) it.copy(isEditing = true)
                                    else it.copy(isEditing = false)
                                }.toMutableList()
                                selectedId = t.id
                            }
                        }
                    )
                }
                .pointerInput(selectedId) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        selectedId?.let { id ->
                            items = items.map {
                                if (it.id == id) {
                                    it.copy(
                                        position = it.position + pan,
                                        scale = (it.scale * zoom).coerceIn(0.5f, 5f)
                                    )
                                } else it
                            }.toMutableList()
                        }
                    }
                }
        )

        // 3. Editable Layer - TextField for editing
        items.forEach { item ->
            if (item.isEditing) {
                val focusRequester = remember { FocusRequester() }
                LaunchedEffect(item.id) {
                    focusRequester.requestFocus()
                }

                BasicTextField(
                    value = item.text,
                    onValueChange = { newText ->
                        items = items.map {
                            if (it.id == item.id) it.copy(text = newText) else it
                        }.toMutableList()
                    },
                    textStyle = TextStyle(
                        fontSize = (item.fontSize * item.scale).sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .offset {
                            IntOffset(item.position.x.roundToInt(), item.position.y.roundToInt())
                        }
                        .graphicsLayer(
                            scaleX = item.scale,
                            scaleY = item.scale
                        )
                        .background(Color.White)
                        .focusRequester(focusRequester)
                        .focusable()
                )
            }
        }
    }
}


