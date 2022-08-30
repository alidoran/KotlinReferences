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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            composContent()
        }
    }
}

@Composable
fun composContent() {
    KotlinReferencesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            layoutType()
        }
    }
}

@Composable
fun layoutType() {
    Column {
        column("column")
        Text("------------------")
        row("row")
        Text("------------------")
        box("box")
        Text("------------------")
        sizableView()
        Text("------------------")
    }
}

@Composable
fun column(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Column() {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
fun row(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Row {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
fun box(type: String) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Box {
            Text(text = "This is a ")
            Text(text = type)
        }
    }
}

@Composable
fun sizableView() {
    Surface(
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.fillMaxWidth().padding(4.dp, 4.dp)
    ) {
        Column {
            Text("sizable modifier")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LayoutDefaultPreview() {
    KotlinReferencesTheme {
        composContent()
    }
}