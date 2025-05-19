package alidoran.android.canvas

import alidoran.android.canvas.textincanvas.CanvasScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.canvas.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinReferencesTheme {
                CanvasScreen()
            }
        }
    }
}

@Composable
fun ComposeCanvas(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        InteractiveTextCanvas()
        ScrollableCanvas()
    }
}

@Composable
fun ScrollableCanvas() {
    // Canvas dimensions
    val canvasWidth = 2000f
    val canvasHeight = 2000f

    // Viewport size
    val viewportWidth = 400.dp
    val viewportHeight = 400.dp

    // Scroll offsets
    val scrollX = remember { mutableFloatStateOf(0f) }
    val scrollY = remember { mutableFloatStateOf(0f) }

    val density = LocalDensity.current

    val scrollStateX = rememberDraggableState { delta ->
        val maxX = with(density) { canvasWidth - viewportWidth.toPx() }
        scrollX.floatValue = (scrollX.floatValue - delta).coerceIn(0f, maxX)
    }

    val scrollStateY = rememberDraggableState { delta ->
        val maxY = with(density) { canvasHeight - viewportHeight.toPx() }
        scrollY.floatValue = (scrollY.floatValue - delta).coerceIn(0f, maxY)
    }

    Box(
        modifier = Modifier
            .size(viewportWidth, viewportHeight)
            .background(Color.LightGray)
            .draggable(
                orientation = Orientation.Horizontal,
                state = scrollStateX
            )
            .draggable(
                orientation = Orientation.Vertical,
                state = scrollStateY
            )
            .clipToBounds()
    ) {
        Canvas(
            modifier = Modifier
                .size(canvasWidth.dp, canvasHeight.dp)
                .graphicsLayer {
                    translationX = -scrollX.floatValue
                    translationY = -scrollY.floatValue
                }
        ) {
            drawRect(Color.Red, size = size)
            drawCircle(Color.Blue, radius = 100f, center = Offset(300f, 300f))
        }
    }
}

@Composable
fun BasicCanvasDemo() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        // Draw a blue line
        drawLine(
            color = Color.Blue,
            start = Offset(100f, 100f),
            end = Offset(300f, 300f),
            strokeWidth = 5f
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinReferencesTheme {
        ComposeCanvas()
    }
}