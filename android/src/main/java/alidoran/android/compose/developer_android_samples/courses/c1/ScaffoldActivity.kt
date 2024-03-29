@file:Suppress("SameParameterValue")

package alidoran.android.compose.developer_android_samples.courses.c1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.commonlibrary.fake_endpoint.ListModel
import com.example.commonlibrary.fake_endpoint.generateMessageList

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                ScaffoldLearn()
            }
        }
    }
}

@Composable
private fun ScaffoldLearn() {
    Scaffold(
        topBar = { TopAppbarSample(topAppBarText = "Top Doran Bar") },
        content = { ContentSample(it, generateMessageList(100)) },
        bottomBar = { BottomAppbarSample(bottomAppBarText = "Bottom Ali Bar") }
    )
}

@Composable
private fun TopAppbarSample(
    topAppBarText: String
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = topAppBarText,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    })
}

@Composable
private fun BottomAppbarSample(
    bottomAppBarText: String
) {
    BottomAppBar { Text(text = bottomAppBarText) }
}

@Composable
private fun ContentSample(
    contentPadding: PaddingValues = PaddingValues(),
    composeList: List<ListModel>
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentPadding = contentPadding
    ) {
        items(composeList){
            Text(text = it.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentSamplePreview() {
    KotlinReferencesTheme {
        ScaffoldLearn()
    }
}