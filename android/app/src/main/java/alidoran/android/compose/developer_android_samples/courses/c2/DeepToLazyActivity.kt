package alidoran.android.compose.developer_android_samples.courses.c2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.commonlibrary.fake_endpoint.generateMessageList
import kotlinx.coroutines.launch

class DeepToLazyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeepToLazy()
                }
            }
        }
    }
}

@Composable
private fun DeepToLazy(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf("Information Box") }
    Scaffold(
        modifier = modifier,
        topBar = {
            ShowInformation(
                { text },
                Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
        },
        content = {
            LazyDeep(
                state,
                Modifier.padding(it)
            )
        },
        bottomBar = {
            ButtonsAct(
                onFirstRow = { text = "The first visible item is: ${state.firstVisibleItemIndex}" },
                onFirstVisibleIcon = {
                    text =
                        "The first visible item scroll offset is: ${state.firstVisibleItemScrollOffset}"
                },
                onTotalItemCount = {
                    text = "The total items count is: ${state.layoutInfo.totalItemsCount}"
                },
                onVisibleItemInfo = {
                    text = "The item info is: ${state.layoutInfo.visibleItemsInfo}"
                },
                onScrollItemThree = { coroutineScope.launch { state.scrollToItem(3) } },
                onAnimatedScrollItemThree = { coroutineScope.launch { state.animateScrollToItem(3) } }
            )
        }
    )
}

@Composable
private fun LazyDeep(state: LazyListState, modifier: Modifier = Modifier) {
    val list = generateMessageList(100)
    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        items(list) {
            Text(modifier = Modifier.padding(4.dp), text = it.body)
        }
    }
}

@Composable
private fun ShowInformation(text: () -> String, modifier: Modifier = Modifier) {
    Text(modifier = modifier.padding(8.dp), text = text.invoke(), textAlign = TextAlign.Center)
}

@Composable
private fun ButtonsAct(
    onFirstRow: () -> Unit,
    onFirstVisibleIcon: () -> Unit,
    onVisibleItemInfo: () -> Unit,
    onTotalItemCount: () -> Unit,
    onScrollItemThree: () -> Unit,
    onAnimatedScrollItemThree: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberScrollState()
    Row(
        modifier = Modifier.horizontalScroll(state),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = onFirstRow) { Text(text = "First row") }
        Button(onClick = onFirstVisibleIcon) { Text(text = "First Visible Icon") }
        Button(onClick = onVisibleItemInfo) { Text(text = "Item info") }
        Button(onClick = onTotalItemCount) { Text(text = "Item count") }
        Button(onClick = onScrollItemThree) { Text(text = "Jump to 3") }
        Button(onClick = onAnimatedScrollItemThree) { Text(text = "Jump to 3 A") }
    }
}


@Preview(showBackground = true)
@Composable
private fun LazyDeepPreview() {
    KotlinReferencesTheme {
        DeepToLazy()
    }
}