package alidoran.android.compose.advance

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FeatureListScreen(navigation: NavController) {
    KotlinReferencesTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {navigation.navigate(ChooseScreen.CoroutinesInComposeScreen.name) }) {
                Text(text = "CoroutinesOnCompose")
            }
            Button(onClick = {navigation.navigate(ChooseScreen.DataSourceUserListScreen.name) }) {
                Text(text = "DataSource")
            }
            Button(onClick = {navigation.navigate(ChooseScreen.PreviewsScreen.name) }) {
                Text(text = "Previews")
            }
            Button(onClick = {navigation.navigate(ChooseScreen.AnimationScreen.name) }) {
                Text(text = "AnimationScreen")
            }
        }
    }
}

@Preview
@Composable
fun FeatureListPreview(){
    FeatureListScreen(rememberNavController())
}