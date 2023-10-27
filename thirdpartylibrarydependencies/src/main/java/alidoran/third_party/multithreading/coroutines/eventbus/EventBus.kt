package alidoran.third_party.multithreading.coroutines.eventbus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

object EventBus {
    private val events = MutableSharedFlow<Any>()

    suspend fun dispatch(event: Any) {
        delay(TimeUnit.SECONDS.toMillis(2))
        events.emit(event)
    }

    suspend fun on(coroutineScope: CoroutineScope, handler: suspend (Any) -> Unit) =
        coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
            delay(TimeUnit.SECONDS.toMillis(1))
            events.asSharedFlow().collect { event -> handler(event) }
        }
}