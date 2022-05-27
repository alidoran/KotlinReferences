package ir.alidoran.teach_kotlin

class CodeNameClass(val code: Int, val name: String?)

@CreateAnnotation
data class AnnotatedCodeNameClass(val code: Int, val name: String?)

// In this class, we have 3 methods for variable and one constructor because Val variable don't have setter
class IdFamilyClass(val id: Int, val family: String)

class RectAngel(private val height:Int, private val width: Int) {
    val isSquare: Boolean
        get() {
            return height==width
        }
}