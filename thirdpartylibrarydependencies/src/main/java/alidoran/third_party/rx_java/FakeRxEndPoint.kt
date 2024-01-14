package alidoran.third_party.rx_java

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

object FakeRxEndPoint {
    suspend fun fakeRxJavaCall(): Observable<String> {
        delay(600)
        return Observable.just("testResponse")
    }

    fun fakeRxJavaCompletableCall(isSuccess: Boolean): Completable {
        return if (isSuccess)
        Completable.complete().delay(5, TimeUnit.SECONDS)
        else Completable.error {ExceptionInInitializerError()}
    }
}