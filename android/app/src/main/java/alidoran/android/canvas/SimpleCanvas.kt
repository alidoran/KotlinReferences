package alidoran.android.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCanvas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            // Draw a red circle in the center
            drawCircle(
                color = Color.Red,
                radius = 80f,
                center = center
            )

            // Draw a blue line
            drawLine(
                color = Color.Blue,
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = 5f
            )
        }
    }
}