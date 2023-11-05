package alidoran.android.compose.developer_android_samples.courses

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FirstCourseAnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                LoadAll()
            }
        }
    }
}

@Composable
private fun LoadAll(){
    Column(
        Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppliedAnimation()
    }
}

@Composable
private fun AppliedAnimation() {
    var isExpended by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(
        targetValue = if (isExpended) 100.dp else 50.dp, label = "",
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    Column(
        modifier = Modifier
            .height(size)
            .fillMaxWidth(1f)
            .padding(5.dp)
            .clickable { isExpended = !isExpended }
            .background(color = Color.Green)
    )
    {

    }
}

@Preview
@Composable
private fun MyAppPreview() {
    KotlinReferencesTheme {
        LoadAll()
    }
}