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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.commonlibrary.fake_endpoint.PictureModel
import com.example.commonlibrary.fake_endpoint.generatePictureList

class DeepToGridActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeepToGrid()
                }
            }
        }
    }
}

@Composable
private fun DeepToGrid(modifier: Modifier = Modifier) {
    val state = rememberLazyGridState()
    var gridCells by remember { mutableStateOf<GridCells>(GridCells.Adaptive(80.dp)) }
    var arrangement by remember { mutableStateOf(Arrangement.SpaceBetween) }
    Scaffold(
        modifier = modifier,
        content = {
            VerticalGrid(
                state = state,
                onArrangement = { arrangement },
                onGridCell = { gridCells },
                modifier = Modifier.padding(it)
            )
        },
        bottomBar = {
            BottomBarButtons(
                onArrangement = { arrangement = it },
                onGridCell = { gridCells = it }
            )
        }
    )
}


@Composable
private fun VerticalGrid(
    state: LazyGridState,
    onArrangement: () -> Arrangement.HorizontalOrVertical,
    modifier: Modifier = Modifier,
    onGridCell: () -> GridCells
) {
    val list = generatePictureList(100)
    LazyVerticalGrid(
        columns = onGridCell.invoke(),
        horizontalArrangement = onArrangement.invoke(),
        state = state,
        modifier = modifier
    ) {
        item (span = { GridItemSpan(maxLineSpan) }){
            Text(text = "Hi")
        }
        items(
            items = list,
            span = { GridItemSpan(if (it.title.contains("3")) 2 else 1) }
        ) {
            GridItem(it)
        }
    }
}

@Composable
private fun GridItem(pictureModel: PictureModel) {
    Row(
        Modifier
            .background(Color.LightGray)
    ) {
        Image(painterResource(id = pictureModel.image), contentDescription = "")
        Text(text = pictureModel.title)
    }
}

@Composable
private fun BottomBarButtons(
    onArrangement: (Arrangement.HorizontalOrVertical) -> Unit,
    onGridCell: (GridCells) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberScrollState()
    Row(
        modifier = modifier.horizontalScroll(state)
    ) {
        Button(onClick = { onArrangement(Arrangement.spacedBy(16.dp)) }) { Text(text = "Space by") }
        Button(onClick = { onArrangement(Arrangement.SpaceBetween) }) { Text(text = "Space between") }
        Button(onClick = { onGridCell(GridCells.Fixed(2)) }) { Text(text = "GridCell fixed 2") }
        Button(onClick = { onGridCell(GridCells.Fixed(3)) }) { Text(text = "GridCell fixed 3") }
        Button(onClick = { onGridCell(GridCells.Adaptive(80.dp)) }) { Text(text = "GridCell adaptive 80dp") }
        Button(onClick = { onGridCell(GridCells.Adaptive(150.dp)) }) { Text(text = "GridCell adaptive 150dp") }
    }
}

@Preview(showBackground = true)
@Composable
private fun VerticalGridPreview() {
    KotlinReferencesTheme {
        DeepToGrid()
    }
}