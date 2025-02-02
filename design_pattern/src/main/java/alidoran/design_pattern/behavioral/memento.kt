package alidoran.design_pattern.behavioral

import android.os.Build
import androidx.annotation.RequiresApi

// Memento: Stores state
data class EditorState(val text: String)

// Originator: Creates & restores state
class Editor {
    var text: String = ""

    fun save() = EditorState(text) // Saves state
    fun restore(state: EditorState) { text = state.text } // Restores state
}

// Caretaker: Manages mementos
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
class History {
    private val states = mutableListOf<EditorState>()

    fun push(state: EditorState) = states.add(state)
    fun pop(): EditorState? = if (states.isNotEmpty()) states.removeLast() else null
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun main() {
    val editor = Editor()
    val history = History()

    editor.text = "Version 1"
    history.push(editor.save()) // Save state

    editor.text = "Version 2"
    history.push(editor.save()) // Save state

    println("Current: ${editor.text}") // Output: Version 2
    editor.restore(history.pop()!!)  // Undo
    println("After Undo: ${editor.text}") // Output: Version 1
}
