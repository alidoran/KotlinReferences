package alidoran.android.canvas.textincanvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CanvasTextItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    var x: Float,
    var y: Float,
    var fontSize: Float,
    var color: Int,
    val textWidth: Float = 0f,
    val textHeight: Float = 0f,
    var fontWeight: FontWeightOption = FontWeightOption.Normal,
) {
    fun toOffset(): Offset = Offset(x, y)
    fun toColor(): Color = Color(color)
}
