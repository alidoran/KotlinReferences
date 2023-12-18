package alidoran.android.compose.advance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AnimationComposeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Ripple effect")
        Column {
            Button(
                onClick = { navController.popBackStack() }) {
                Text(
                    modifier = Modifier.padding(200.dp),
                    text = "Ali Doran"
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Ali Doran"
                )
            }
        }
    }
}