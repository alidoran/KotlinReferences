package alidoran.android.compose.developer_android_samples.courses.c2

import alidoran.android.compose.ui.theme.CustomTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import alidoran.android.compose.ui.theme.md_theme_light_background
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp

class M3ThemesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M3Themes()

        }
    }
}

@Composable
private fun M3Themes(modifier: Modifier = Modifier) {
    val isCustomState = rememberSaveable { mutableStateOf(false) }
    if (isCustomState.value)
        CustomTheme {
            Screen(isCustomState)
        }
    else
        KotlinReferencesTheme {
            Screen(isCustomState)
        }
}

@Composable
private fun Screen(state: MutableState<Boolean>, modifier: Modifier = Modifier) {
    Scaffold(
        content = { M3ThemesBody(modifier = Modifier.padding(it)) },
        bottomBar = {
            M3ThemesBottomBar({ state.value = !state.value })
        },
        containerColor = md_theme_light_background
    )
}

@Composable
private fun M3ThemesBody(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(Modifier.fillMaxHeight()) {
            TextItem(Modifier.background(color = MaterialTheme.colorScheme.inversePrimary))
            TextItem(Modifier.background(color = MaterialTheme.colorScheme.inverseOnSurface))
            TextItem(Modifier.background(color = MaterialTheme.colorScheme.tertiary))
        }
    }
}

@Composable
private fun TextItem(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "This is a sample text to show the Material Design 3 UI changes",
    )
}

@Composable
private fun M3ThemesBottomBar(onChangeTheme: () -> Unit, modifier: Modifier = Modifier) {
    val state = rememberScrollState()
    Surface(
        tonalElevation = 5.dp,
        shadowElevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .horizontalScroll(state)
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = onChangeTheme) { Text(text = "Change App Themes") }
        }
    }
}

@Composable
fun Test(uName: String){
    Text(text = uName)
}

@Preview(showBackground = true)
@Composable
private fun M3ThemesPreview() {
    M3Themes()
}

@Composable
@Preview
fun TestPreview(){
Test(uName = "A")
}
