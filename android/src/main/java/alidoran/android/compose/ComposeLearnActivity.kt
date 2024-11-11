package alidoran.android.compose

import alidoran.android.compose.developer_android_samples.DeveloperAndroidActivity
import alidoran.android.compose.advance.ComposeAdvanceActivity
import alidoran.android.compose.navigation.ComposeNavigationActivity
import alidoran.android.compose.state_hosting.StateHostingActivity
import alidoran.android.compose.viewmodel_hosting_structure.ViewModelHoistingActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ComposeLearnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetButtons()
        }
    }

    @Composable
    private fun SetButtons() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                startActivity(
                    Intent(this@ComposeLearnActivity, DeveloperAndroidActivity::class.java)
                )
            }) { Text(text = "developer.android") }
            Button(onClick = {
                startActivity(Intent(this@ComposeLearnActivity, ComposeAdvanceActivity::class.java))
            }) { Text(text = "Advanced Compose") }

            Button(onClick = {
                startActivity(Intent(this@ComposeLearnActivity, ComposeNavigationActivity::class.java))
            }) { Text(text = "Compose Navigation Activity") }

            Button(onClick = {
                startActivity(Intent(this@ComposeLearnActivity, StateHostingActivity::class.java))
            }) { Text(text = "State Hosting Activity") }

            Button(onClick = {
                startActivity(Intent(this@ComposeLearnActivity, ViewModelHoistingActivity::class.java))
            }) { Text(text = "viewmodel Hosting structure Activity") }

        }
    }
}