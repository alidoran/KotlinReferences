@file:Suppress("SameParameterValue")

package alidoran.android.compose

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposContent()
        }
    }
}

@Composable
private fun ComposContent() {
    KotlinReferencesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LayoutType()
        }
    }
}

@Composable
private fun LayoutType() {
    Column {
        ColumnSample("column")
        Text("------------------")
        RowSample("row")
        Text("------------------")
        BoxSample("box")
        Text("------------------")
        SizableView()
        Text("------------------")
    }
}

@Composable
private fun ColumnSample(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Column {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
private fun RowSample(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Row {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
private fun BoxSample(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Box {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
fun SizableView() {
    Surface(
        color = MaterialTheme.colorScheme.error,

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(4.dp, 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("sizable modifier")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LayoutDefaultPreview() {
    KotlinReferencesTheme {
        ComposContent()
    }
}