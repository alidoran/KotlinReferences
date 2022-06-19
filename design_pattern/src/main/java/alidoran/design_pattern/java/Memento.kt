package alidoran.design_pattern.java

class Editor {
    var content: String = "defValue"

    fun createState(): EditorState {
        return EditorState(content)
    }

    fun restore(state: EditorState){
        content = state.content
    }
}

data class EditorState(val content: String)

class History{
    private val states = ArrayList<EditorState>()

    fun push(state: EditorState){
        states.add(state)
    }

    fun pop(): EditorState {
        val lastState = states.last()
        states.remove(states.last())
        return lastState
    }
}

fun main() {
    val editor = Editor()
    val history = History()

    editor.content = "a"
    history.push(editor.createState())

    editor.content = "b"
    history.push(editor.createState())


    editor.content = "c"
    println(editor.content)
    editor.restore(history.pop())
    println(editor.content)
}

