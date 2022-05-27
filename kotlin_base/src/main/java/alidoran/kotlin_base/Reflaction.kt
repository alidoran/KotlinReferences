package ir.alidoran.teach_kotlin

import java.lang.reflect.Field

fun main() {
    val codeNameModel = AnnotatedCodeNameClass(1840, "AliDoran")
    callReflect(codeNameModel)
}

fun <T> callReflect(inputModel: T) {

    val declaredClassFields = inputModel!!::class.java.declaredFields
    for (field in declaredClassFields){
     println("Name: ${field.name} | type: ${field.type}")
    }
    //Annotation reflect is needed "kotlin-reflect:1.6.20" in gradle
    println(inputModel!!::class.annotations)
}