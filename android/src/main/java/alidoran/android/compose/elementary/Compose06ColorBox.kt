package alidoran.android.compose.elementary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

class Compose06ColorBox : ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent {
            Column() {
                ColorBox1(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                )

                val color = remember {
                    mutableStateOf(Color.Yellow)
                }
                ColorBox2(
                    Modifier
                        .weight(1f)
                        .background(color.value)
                        .fillMaxSize()){
                            color.value = it
                        }
            }
        }
    }

    @Composable
    fun ColorBox1(modifier: Modifier = Modifier) {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }

        Box(modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            })
    }

    @Composable
    fun ColorBox2(
        modifier: Modifier = Modifier,
        updateColor: (Color) -> Unit
    ) {
        Box(modifier = modifier
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            })
    }


    @Preview
    @Composable
    fun LayoutDefaultPreview() {
        ColorBox1(
            Modifier
                .fillMaxSize()
        )
    }
}

