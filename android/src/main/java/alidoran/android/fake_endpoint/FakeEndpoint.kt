package alidoran.android.fake_endpoint

import alidoran.android.mvi.basic_mvi.model.UserMviModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

object FakeEndpoint {
    private const val sampleString = "This is the fake API result"

    suspend fun fakeStringRequest():String {
        delay(TimeUnit.SECONDS.toMillis(3))
        return sampleString
    }

    suspend fun fakeMviModelListRequest():ArrayList<UserMviModel> {
        delay(TimeUnit.SECONDS.toMillis(3))
        val stringList =  sampleString.split(" ")
        val result= ArrayList<UserMviModel>()
        for (s in stringList){
            result.add(UserMviModel(s))
        }
        return result
    }

    suspend fun fakeMviModelListRequestFlow(): Flow<ArrayList<UserMviModel>> {
        delay(TimeUnit.SECONDS.toMillis(3))
        val stringList =  sampleString.split(" ")
        val result= ArrayList<UserMviModel>()
        for (s in stringList){
            result.add(UserMviModel(s))
        }
        return MutableStateFlow(result)
    }

    fun fakeRepeatCallApi(): Flow<LocalDateTime> {
        val time: Flow<LocalDateTime> = flow {
            //region Producer block
            repeat(5) {
                val timeString = LocalDateTime.now()
                emit(timeString)
                delay(2000)
            }
            //endregion
        }
        return time
    }
}


