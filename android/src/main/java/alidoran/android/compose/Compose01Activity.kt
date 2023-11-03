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

@Suppress("SameParameterValue")
class Compose01Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposContent()
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
            SizableView1()
            Text("------------------")
            SizableView2()
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
    private fun SizableView1() {
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
    private fun SizableView2() {
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
    private fun LayoutDefaultPreview() {
        KotlinReferencesTheme {
            ComposContent()
        }
    }
}
