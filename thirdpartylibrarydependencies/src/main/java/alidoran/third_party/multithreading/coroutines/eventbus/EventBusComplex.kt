package alidoran.third_party.multithreading.coroutines.eventbus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

object EventBusComplex {
    val events = MutableSharedFlow<Any>()

    suspend fun post(event: Any) {
        events.emit(event)
    }

    suspend inline fun <reified T : Any> register(crossinline handler: suspend (T) -> Unit) =
        CoroutineScope(coroutineContext).launch(start = CoroutineStart.UNDISPATCHED) {
            events.asSharedFlow().collect {
                if (it is T) handler.invoke(it)
            }
        }
}