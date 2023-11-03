package alidoran.android.compose.developer_tutorial

import android.os.Bundle
import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview

class DeveloperAndroidActivity : ComponentActivity() {
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
                    Intent(
                        this@DeveloperAndroidActivity,
                        FirstTillThreeActivity::class.java
                    )
                )
            }) {
                Text(text = "First till third")
            }
            Button(onClick = {
                startActivity(
                    Intent(
                        this@DeveloperAndroidActivity,
                        FourthActivity::class.java
                    )
                )
            }) {
                Text(text = "Fourth")
            }
        }
    }

    @Preview
    @Composable
    fun PagePreview() {
        SetButtons()
    }
}