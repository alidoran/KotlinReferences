package alidoran.design_pattern.structural

interface Component {
    fun show()
}

class File(private val name: String) : Component {
    override fun show() = println("File: $name")
}

class Folder(private val name: String) : Component {
    private val children = mutableListOf<Component>()

    fun add(component: Component) = children.add(component)

    override fun show() {
        println("Folder: $name")
        children.forEach { it.show() }
    }
}

fun main() {
    val file1 = File("Document.txt")
    val file2 = File("Photo.jpg")

    val folder = Folder("MyFiles")
    folder.add(file1)
    folder.add(file2)

    folder.show()
}
