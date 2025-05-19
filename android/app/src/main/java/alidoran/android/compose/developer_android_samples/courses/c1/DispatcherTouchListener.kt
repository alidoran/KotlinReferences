package alidoran.android.compose.developer_android_samples.courses.c1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import alidoran.android.compose.developer_android_samples.courses.c1.ui.theme.KotlinReferencesTheme
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class DispatcherTouchListener : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinReferencesTheme {
                LazyColumnWithTwoFingerScroll()
            }
        }
    }
}

class MultiTouchListener : View.OnTouchListener {
    private val handlers = mutableListOf<(MotionEvent) -> Boolean>()

    // Add handler functions that will be called for different touch events
    fun addHandler(handler: (MotionEvent) -> Boolean) {
        handlers.add(handler)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        // Process all handlers and return true if any handler consumes the event
        var consumed = false
        for (handler in handlers) {
            if (handler(event)) consumed = true
        }
        return consumed
    }
}

class OneFingerReverseScrollHandler(
    private val coroutineScope: CoroutineScope,
    private val scrollBy: (Float) -> Unit
) {
    fun getHandler(): (MotionEvent) -> Boolean {
        var lastY: Float = 0f
        return fun(event: MotionEvent): Boolean {
            if (event.pointerCount != 1) return false

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    lastY = event.getY(0)
                }

                MotionEvent.ACTION_MOVE -> {
                    val y = event.getY(0)
                    val dy = lastY - y
                    lastY = y

                    coroutineScope.launch {
                        scrollBy(-dy) // Reversed direction
                    }
                }
            }
            return true
        }
    }
}

class TwoFingerTouchHandler(
    private val coroutineScope: CoroutineScope,
    private val scrollBy: (Float) -> Unit
) {
    fun getHandler(): (MotionEvent) -> Boolean {
        var lastY = 0f
        return fun(event: MotionEvent): Boolean {
            if (event.pointerCount != 2) {
                return false
            }

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    lastY = event.getY(0)
                }

                MotionEvent.ACTION_MOVE -> {
                    val avgY = (0 until event.pointerCount)
                        .map { event.getY(it) }
                        .average()
                        .toFloat()
                    val dy = lastY - avgY
                    lastY = avgY

                    coroutineScope.launch {
                        scrollBy(dy)
                    }
                }
            }
            return true
        }
    }
}

@Composable
fun LazyColumnWithTwoFingerScroll() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val items = List(100) { "Item $it" }

    val multiTouchHandler = remember {
        MultiTouchListener().apply {
            addHandler(
                OneFingerReverseScrollHandler(coroutineScope) { dy ->
                    coroutineScope.launch {
                        scrollState.scrollBy(dy)
                    }
                }.getHandler()
            )

            addHandler(
                TwoFingerTouchHandler(coroutineScope) { dy ->
                    coroutineScope.launch {
                        scrollState.scrollBy(dy)
                    }
                }.getHandler()
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            userScrollEnabled = false, // Disable default touch scroll
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(items) { index, item ->
                InnerComposableSample(index, item)
            }
        }

        AndroidView(
            factory = { context ->
                View(context).apply {
                    setOnTouchListener(multiTouchHandler)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun InnerComposableSample(
    item: Int,
    index: String,
){
    Text(
        text = "[$index] $item",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(8.dp)
    )
}