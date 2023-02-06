package alidoran.android.compose

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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

class Compose01Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            composContent()
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
            columnSample("column")
            Text("------------------")
            rowSample("row")
            Text("------------------")
            boxSample("box")
            Text("------------------")
            sizableView1()
            Text("------------------")
            sizableView2()
        }
    }

    @Composable
    fun columnSample(type: String) {
        Surface(color = MaterialTheme.colorScheme.error) {
            Column {
                Text(text = "This is a ")
                Text(text = type)
            }
        }
    }

    @Composable
    fun rowSample(type: String) {
        Surface(color = MaterialTheme.colorScheme.error) {
            Row {
                Text(text = "This is a ")
                Text(text = type)
            }
        }
    }

    @Composable
    fun boxSample(type: String) {
        Surface(color = MaterialTheme.colorScheme.error) {
            Box {
                Text(text = "This is a ")
                Text(text = type)
            }
        }
    }

    @Composable
    fun sizableView1() {
        Surface(
            color = MaterialTheme.colorScheme.error,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 4.dp)
                    .fillMaxSize(0.5f)
                    .background(Color.Green),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("sizable")
                Text("modifier")
                Text("sample")
            }
        }
    }

    @Composable
    fun sizableView2() {
        Surface(
            color = MaterialTheme.colorScheme.error,
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp, 4.dp)
                    .fillMaxWidth(0.95f)
                    .height(200.dp)
                    .background(Color.Green),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text("sizable")
                Text("modifier")
                Text("sample")
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
}
