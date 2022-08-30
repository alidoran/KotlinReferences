package alidoran.android.compose

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

class ComponentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            componentContent()
        }
    }
}

@Composable
fun componentContent(){
    KotlinReferencesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Components("Android")
        }
    }
}

@Composable
fun Components(name: String) {
    Column {
        ButtonComponent()
    }
}

@Composable
fun ButtonComponent() {0
    Column {
        Button(
            onClick = {}
        ){
            Text("Button Key")
        }

        OutlinedButton(onClick = {}) {
            Text("Outline button key")
        }

        TextButton(onClick = {}) {
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComponentDefaultPreview() {
    KotlinReferencesTheme {
        Components("Android")
    }
}