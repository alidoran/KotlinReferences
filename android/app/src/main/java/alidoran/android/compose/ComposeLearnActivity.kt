package alidoran.android.compose

import alidoran.android.compose.developer_android_samples.DeveloperAndroidActivity
import alidoran.android.compose.advance.ComposeAdvanceActivity
import alidoran.android.compose.navigation.p1_basic.ComposeNavigationActivity
import alidoran.android.compose.navigation.p2_clean_multi_module.ComposeNavigationCleanActivity
import alidoran.android.compose.navigation.p3_best_approach_multi_module.ComposeNavigationBestActivity
import alidoran.android.compose.state_hosting.StateHostingActivity
import alidoran.android.compose.viewmodel_hosting_structure.ViewModelHoistingActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
            val activity = this@ComposeLearnActivity
            Button(onClick = {
                startActivity(
                    Intent(activity, DeveloperAndroidActivity::class.java)
                )
            }) { Text(text = "developer.android") }
            Button(onClick = {
                startActivity(Intent(activity, ComposeAdvanceActivity::class.java))
            }) { Text(text = "Advanced Compose") }

            Button(onClick = {
                startActivity(Intent(activity, StateHostingActivity::class.java))
            }) { Text(text = "State Hosting Activity") }

            Button(onClick = {
                startActivity(Intent(activity, ViewModelHoistingActivity::class.java))
            }) { Text(text = "viewmodel Hosting structure") }

            Button(onClick = {
                startActivity(Intent(activity, ComposeNavigationActivity::class.java))
            }) { Text(text = "01 Basic Compose Navigation") }

            Button(onClick = {
                startActivity(Intent(activity, ComposeNavigationCleanActivity::class.java))
            }) { Text(text = "02 Clean Compose multi-module Navigation") }

            Button(onClick = {
                startActivity(Intent(activity, ComposeNavigationBestActivity::class.java))
            }) { Text(text = "03 Best Compose multi-module Navigation") }
        }
    }
}