package com.example.retrofitteach.tools

import com.google.gson.Gson
import java.lang.Exception

fun mapperModel(outputJson: String, type: Class<*>?): Any? {
    try {
        return Gson().fromJson<Any>(outputJson, type)
    } catch (e: Exception) {
        throw IllegalAccessException(e.message)
    }
    return null
}