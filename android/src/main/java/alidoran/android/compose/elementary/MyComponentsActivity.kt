package alidoran.android.compose.elementary

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MyComponentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentContent()
        }
    }
}

@Composable
private fun ComponentContent(){
    KotlinReferencesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Components()
        }
    }
}

@Composable
private fun Components() {
    Column {
        ButtonComponent()
    }
}

@Composable
private fun ButtonComponent() {
    Column {
        Button(
            onClick = {

            }
        ){
            Text("Button Key")
        }

        OutlinedButton(onClick = {}) {
            Text("Outline button key")
        }

        TextButton(onClick = {
        }) {
            Text("TextButton")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ComponentDefaultPreview() {
    KotlinReferencesTheme {
        Components()
    }
}