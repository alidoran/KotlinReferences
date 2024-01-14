package alidoran.third_party

import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

object FakeRx3Api {
    fun fakeUploadImage(isSuccess: Boolean): Flowable<String> {
        return if (isSuccess)
            Flowable.just("UploadIsSuccessful").delay(5, TimeUnit.SECONDS)
        else Flowable.error(ExceptionInInitializerError())
    }

    fun fakeStringFlowable(): Flowable<String> =
            Flowable.just("StringFlowable").delay(5, TimeUnit.SECONDS)
}