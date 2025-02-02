package alidoran.design_pattern.structural

interface Renderer {
    fun renderShape(name: String)
}

class VectorRenderer : Renderer {
    override fun renderShape(name: String) = println("Rendering $name as Vector")
}

class RasterRenderer : Renderer {
    override fun renderShape(name: String) = println("Rendering $name as Bitmap")
}

abstract class Shape(protected val renderer: Renderer) {
    abstract fun draw()
}

class Circle(renderer: Renderer) : Shape(renderer) {
    override fun draw() = renderer.renderShape("Circle")
}

fun main() {
    val circle = Circle(VectorRenderer())
    circle.draw() // Rendering Circle as Vector
}
