package alidoran.design_pattern.structural

interface Image {
    fun display()
}

class RealImage(private val filename: String) : Image {
    init {
        println("Loading image from disk: $filename")
    }

    override fun display() = println("Displaying image: $filename")
}

class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) realImage = RealImage(filename)
        realImage?.display()
    }
}

fun main() {
    val image = ProxyImage("photo.jpg")
    image.display() // Loads and displays
    image.display() // Only displays (no reload)
}
