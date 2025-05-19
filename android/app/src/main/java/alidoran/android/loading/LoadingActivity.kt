package alidoran.android.loading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.loading.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinReferencesTheme {
                Loading()
            }
        }
    }
}

@Composable
fun Loading() {
    var isLoading by remember { mutableStateOf(false) }
    var load by remember { mutableStateOf(false) }
    LaunchedEffect(load) {
        if (load) {
            isLoading = true
            delay(2000)
            isLoading = false
            load = false
        }
    }

    LoadingContent(
        isLoading = isLoading,
        placeholder = { SkeletonPlaceholder(
            componentCount = 4,
            componentsSize = listOf(null, null, null, 50.dp)
        ) },
        content = {
            Column(Modifier.padding(16.dp)) {
                Text("Title = Title")
                Text("Description = Description")
                Text("Summary = Summary")
                Button(
                    onClick = { load = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("load")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinReferencesTheme {
        Loading()
    }
}