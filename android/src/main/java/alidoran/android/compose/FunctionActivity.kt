package alidoran.android.compose

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FunctionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunctionContents()
        }
    }
}

@Composable
fun FunctionContents() {

    KotlinReferencesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Functions()
        }
    }
}

@Composable
fun Functions() {
    Column {
        LoopInTheColumn()
    }
}

@Composable
fun LoopInTheColumn() {
    val expanded = remember { mutableStateOf(false) }
    Surface(color = MaterialTheme.colorScheme.error) {
        Column {
            for (i in 0..2) {
                Text("number = $i")
            }
            RememberValue()
            expand()
        }
    }
}

@Composable
fun RememberValue() {
    val expanded = remember { mutableStateOf(false) }
    Button(
        onClick = {
            expanded.value = !expanded.value
        }
    ) {
        Text(expanded.value.toString())
    }
}

@Composable
fun expand() {
    Column {
        val expanded = remember { mutableStateOf(false) }
        val extraPadding = if (expanded.value) 48.dp else 0.dp

        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello, ")
                    Text(text = "World")
                }

                OutlinedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun FunctionDefaultPreview() {
    KotlinReferencesTheme {
        Functions()
    }
}