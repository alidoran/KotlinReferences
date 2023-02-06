package alidoran.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class Compose08List : ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent {
//            ListView()
//            listViewAsRecycler()
            listViewAsRecyclerIndex()
        }
    }

    @Composable
    fun ListView() {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            for (i in 0..50) {
                Text(
                    text = "Item $i",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }

    @Composable
    fun listViewAsRecycler() {
        LazyColumn {
            items(5000) {
                Text(
                    text = "Item $it",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }

    @Composable
    fun listViewAsRecyclerIndex() {
        val list = ArrayList<String>()
        for (i in 0..5000) {
            list.add("Item $i")
        }

        LazyColumn{
            itemsIndexed(
                list
            ) { index, string ->
                Text(
                    text = "num $index is $string",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                    )
            }
        }
    }

    @Preview
    @Composable
    fun LayoutDefaultPreview() {
        listViewAsRecyclerIndex()
    }
}