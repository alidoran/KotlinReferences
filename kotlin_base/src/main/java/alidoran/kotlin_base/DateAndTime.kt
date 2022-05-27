package ir.alidoran.teach_kotlin

import java.time.LocalDateTime
import java.util.*

fun main() {
    localNowTime()
}

fun localNowTime(){
    //println(LocalDateTime.now().toString()) //2021-10-25T12:03:04.524
    println(Calendar.getInstance().time) //Mon Oct 25 12:02:23 GST 2021
}