package alidoran.android.compose.developer_android_samples.courses.c1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.nestedscroll.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

class DispatcherConnectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NestedScrollDispatcherSample()
            }
        }
    }
}

@Composable
fun NestedScrollDispatcherSample() {
    val dispatcher = remember { NestedScrollDispatcher() }

    val parentNestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                println("PARENT: onPreScroll $available")
                return Offset.Zero
            }


            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                println("PARENT: onPostScroll $available after consuming $consumed")
                return Offset.Zero
            }
        }
    }

    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(parentNestedScrollConnection)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .background(Color.LightGray)
                .nestedScroll(connection = object : NestedScrollConnection {}, dispatcher = dispatcher)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragStart = {
                            dispatcher.dispatchPreScroll(Offset.Zero, NestedScrollSource.Drag)
                        },
                        onVerticalDrag = { _, dragAmount ->
                            val delta = Offset(0f, -dragAmount)
                            val preConsumed = dispatcher.dispatchPreScroll(delta, NestedScrollSource.Drag)
                            val remaining = delta - preConsumed

                            offsetY += remaining.y // Apply movement
                            dispatcher.dispatchPostScroll(preConsumed, remaining, NestedScrollSource.Drag)
                        }
                    )
                }
        ) {
            Text("Drag me vertically", modifier = Modifier.align(Alignment.Center))
        }
    }
}
