package alidoran.android.compose

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
fun Functions(){
    Column {
        LoopInTheColumn()
    }
}

@Composable
fun LoopInTheColumn() {
    Surface(color = MaterialTheme.colorScheme.error) {
        Column {
            for (i in 0..2) {
                Text("number = $i")
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