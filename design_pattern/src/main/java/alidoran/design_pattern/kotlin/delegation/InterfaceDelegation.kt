package alidoran.design_pattern.kotlin.delegation

interface Printer {
    fun print()
}

class PrinterImpl(val text: String) : Printer {
    override fun print() {
        println(text)
    }
}

class Document(printer: Printer) : Printer by printer

fun main() {
    val printer = PrinterImpl("Hello, World!")
    val document = Document(printer)
    document.print()  // Prints: Hello, World!
}
