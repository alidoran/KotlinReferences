@file:Suppress("unused")

package com.example.commonlibrary.fake_endpoint

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import java.util.concurrent.TimeUnit

object FakeEndpoint {
    private const val sampleString = "This is the fake API result"

    suspend fun fakeStringRequest(): Flow<String> {
        delay(TimeUnit.SECONDS.toMillis(3))
        return flow { emit(sampleString) }
    }

    suspend fun fakeStringLongDelayRequest(): Flow<String> {
        delay(TimeUnit.SECONDS.toMillis(5))
        return flow { emit(sampleString) }
    }

    suspend fun fakeStringListRequest(): Flow<List<String>> {
        delay(TimeUnit.SECONDS.toMillis(3))
        return flow { emit(listOf("1", "2", "3", "4")) }
    }

    suspend fun fakeIntRepeatRequest(count: Int): Flow<Int> {
        var value = 0
        delay(TimeUnit.SECONDS.toMillis(3))
        return flow {
            repeat(count) {
                delay(TimeUnit.SECONDS.toMillis(3))
                emit(value++)
            }
        }
    }

    suspend fun fakeChecklistRepeatRequest(count: Int): Flow<CheckListModel> {
        var value = 0
        delay(TimeUnit.SECONDS.toMillis(3))
        return flow {
            repeat(count) {
                delay(TimeUnit.SECONDS.toMillis(3))
                value++
                emit(CheckListModel(value, "Title $value", value % 2 == 0))
            }
        }
    }

    suspend fun fakeCatchRequest(): Flow<Int> {
        delay(TimeUnit.SECONDS.toMillis(3))
        return flow {
            throw (Throwable("Error"))
        }
    }

    suspend fun fakeMviModelListRequest(): ArrayList<NameModel> {
        delay(TimeUnit.SECONDS.toMillis(3))
        val stringList = sampleString.split(" ")
        val result = ArrayList<NameModel>()
        for (s in stringList) {
            result.add(NameModel(s))
        }
        return result
    }

    suspend fun fakeMviModelListRequestFlow(): Flow<ArrayList<NameModel>> {
        delay(TimeUnit.SECONDS.toMillis(3))
        val stringList = sampleString.split(" ")
        val result = ArrayList<NameModel>()
        for (s in stringList) {
            result.add(NameModel(s))
        }
        return MutableStateFlow(result)
    }

    fun fakeCallOneToThreeApi(): Flow<Int> {
        val time: Flow<Int> = flow {
            //region Producer block
            repeat(2) {
                delay(1000)
                emit(1)
                emit(2)
                emit(3)
            }
            //endregion
        }.shareIn(
            MainScope(), SharingStarted.WhileSubscribed(), 1
        )
        return time
    }

    fun longProcess(): Int {
        var a = 3
        repeat(999999999) {
            a++
            a--
            a++
            a--
            a * a
            a / a
        }
        return a
    }
}

