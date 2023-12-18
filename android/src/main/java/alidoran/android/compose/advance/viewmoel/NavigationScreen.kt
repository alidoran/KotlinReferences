package alidoran.android.compose.advance.viewmoel

import alidoran.android.compose.advance.ChooseScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.Navigator

@Composable
fun NavigationScreen(navigation: NavController, popUpClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = popUpClick) { Text(text = "Pop up by NavHost") }
        Button(onClick = {navigation.navigate(ChooseScreen.AnimationScreen.name){
            popUpTo(ChooseScreen.FeatureListScreen.name){
                inclusive = true
            }
        } }) {
            Text(text = "NavigationScreen")
        }
    }
}