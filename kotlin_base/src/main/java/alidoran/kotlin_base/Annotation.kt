package ir.alidoran.teach_kotlin

fun main() {

    callCreatedAnnotation()
}

//region Annotation Attribute
//@Target(AnnotationTarget.CLASS)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS) //Target of annotation
//@Retention(AnnotationRetention.SOURCE)
@Retention(AnnotationRetention.RUNTIME) //annotation compile time
@Repeatable //repeat it in one place in using method
@MustBeDocumented //We use it in the public API while have method documentation
annotation class CreateAnnotation {} //An interface without any attribute
//endregion

//region annotationName
annotation class MarkerAnnotation()
annotation class SingleValueAnnotation(val singleValue: String)
annotation class MultiValueAnnotation(val firstValue: String, val secondValue: Int)
//endregion

//region Deprecated Methods
fun callDeprecatedMethod() {
    deprecatedMethod()
}

@Deprecated(
    "Instead of using this method you can use", ReplaceWith("nonDeprecatedMethod()")
)
fun deprecatedMethod() {
    println("deprecatedMethod")
}

fun nonDeprecatedMethod() {
    println("nonDeprecatedMethod")
}
//endregion

//region Use custom Annotation
fun callCreatedAnnotation() {
    val useOwnerMethodClass = UseOwnerMethodClass::class.java
    val classLevelAnnotation = useOwnerMethodClass.getAnnotation(Owner::class.java)!!
    println("${classLevelAnnotation.ownerName} \n ${classLevelAnnotation.ownerCode}")
    val methods = useOwnerMethodClass.methods
    for (method in methods) {
        val annotation = method.getAnnotation(Owner::class.java)
        if (annotation != null) {
            println(annotation.ownerName)
            println(annotation.ownerCode)
        }
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Owner(val ownerName: String = "ali", val ownerCode: Int = 1362)

@Owner("doran", 4)
class UseOwnerMethodClass {
    @Owner
    fun methodOne() {

    }

    @Owner("Roya", 1363)
    fun methodTwo() {

    }
}
//endregion