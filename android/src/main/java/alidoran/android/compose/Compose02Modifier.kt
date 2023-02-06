package alidoran.android.compose

import alidoran.android.compose.ui.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Compose02Modifier : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeModifier()
                }
            }
        }
    }

    @Composable
    fun ComposeModifier() {
        Column(
            Modifier.fillMaxSize()
        ) {
            dynamicWidth()
            Text(text = "--------------")
            fixedWidth()
            Text(text = "--------------")
            padding()
            Text(text = "--------------")
            border()
            Text(text = "--------------")
            clickable()
        }

    }

    @Composable
    private fun clickable() {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .clickable { }
        ) {
            Text(text = "clickable")
        }

    }

    @Composable
    private fun padding() {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Green)
        ) {
            Text(
                text = "padding", modifier = Modifier
                    .offset(25.dp, 30.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "padding2")
        }
    }

    @Composable
    private fun border() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .border(5.dp, Color.Red)
                .padding(5.dp)
                .border(5.dp, Color.Cyan)
                .padding(5.dp)
                .border(5.dp, Color.Magenta)
                .padding(5.dp)
                .border(5.dp, Color.Blue)
                .padding(5.dp)
        ) {
            Text(text = "border")
        }
    }

    @Composable
    private fun dynamicWidth() {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .width(1200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("dynamicWidth")
        }
    }

    @Composable
    private fun fixedWidth() {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .requiredWidth(1200.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("fixedWidth")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComponentDefaultPreview() {
        KotlinReferencesTheme {
            ComposeModifier()
        }
    }
}