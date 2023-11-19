package alidoran.android.compose

import alidoran.android.compose.developer_android_samples.DeveloperAndroidActivity
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ComposeLearnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetButtons()
        }
    }

    @Composable
    private fun SetButtons(){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                startActivity(
                    Intent(
                        this@ComposeLearnActivity,
                        DeveloperAndroidActivity::class.java
                    )
                )
            }) {
                Text(text = "developer.android")
            }
        }
    }

    @Composable
    fun ABC(a: (Int, Int)-> Unit){

    }




    @Composable
    fun PagePreview(boolean: Boolean){
        Text(text = "$boolean")
    }

    @Preview
    @Composable
    fun TestPreview(
        @PreviewParameter(PreviewBooleanProvider::class) boolean: Boolean
    ){
        PagePreview(boolean)
    }
}

class PreviewBooleanProvider: PreviewParameterProvider<Boolean?>{
    override val values: Sequence<Boolean?> = sequenceOf(
        true,false,null
    )

}