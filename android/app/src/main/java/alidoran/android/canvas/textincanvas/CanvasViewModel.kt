package alidoran.android.canvas.textincanvas

import android.graphics.Paint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.serialization.json.Json
import java.util.UUID

class CanvasViewModel : ViewModel() {
    private val _textItems = mutableStateListOf<CanvasTextItem>()
    val textItems: List<CanvasTextItem> get() = _textItems
    private var jsonText = ""

    fun addText(text: String, position: Offset, fontSize: Float = 40f): String {
        val id = UUID.randomUUID().toString()
        val paint = Paint().apply { this.textSize = fontSize }
        val textWidth = paint.measureText(text)
        val textHeight = paint.fontMetrics.run { bottom - top }

        val item = CanvasTextItem(
            id = id,
            text = text,
            x = position.x,
            y = position.y,
            fontSize = fontSize,
            color = Color.Black.toArgb(),
            textWidth = textWidth,
            textHeight = textHeight
        )
        _textItems += item
        return item.id
    }

    fun updateText(id: String, newText: String) {
        val index = _textItems.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = _textItems.find { it.id == id } ?: return
            val paint = Paint().apply { textSize = item.fontSize }
            val textWidth = paint.measureText(newText)
            val textHeight = paint.fontMetrics.run { bottom - top }

            _textItems[index] = _textItems[index].copy(
                text = newText,
                textWidth = textWidth,
                textHeight = textHeight
            )
        }
    }


    fun saveAsJson() {
        jsonText = Json.encodeToString(textItems)
    }

    fun loadFromJson() {
        val loaded = Json.decodeFromString<List<CanvasTextItem>>(jsonText)
        _textItems.clear()
        _textItems.addAll(loaded)
    }

    fun resetScreen() {
        _textItems.clear()
    }

    fun updatePosition(id: String, newX: Float, newY: Float) {
        val index = _textItems.indexOfFirst { it.id == id }
        if (index == -1) return
        val oldItem = _textItems[index]
        _textItems[index] = oldItem.copy(x = newX, y = newY)
    }

    fun updateFontSize(id: String, fontSize: Float) {
        val index = _textItems.indexOfFirst { it.id == id }
        if (index == -1) return
        val oldItem = _textItems[index]
        _textItems[index] = oldItem.copy(fontSize = fontSize)
    }

    fun updateColor(id: String, color: Color) {
        val index = _textItems.indexOfFirst { it.id == id }
        if (index == -1) return
        val oldItem = _textItems[index]
        _textItems[index] = oldItem.copy(color = color.toArgb())
    }

    fun updateFontWeight(id: String, fontWeight: FontWeightOption) {
        val index = _textItems.indexOfFirst { it.id == id }
        if (index == -1) return
        val oldItem = _textItems[index]
        _textItems[index] = oldItem.copy(fontWeight = fontWeight)
    }

}
