package alidoran.android.compose.developer_android_samples

import alidoran.android.compose.developer_android_samples.courses.c1.CodeLabBasicLayoutActivity
import alidoran.android.compose.developer_android_samples.courses.c1.FirstCourseActivity
import alidoran.android.compose.developer_android_samples.courses.c1.FirstCourseAnimationActivity
import alidoran.android.compose.developer_android_samples.courses.c1.ScaffoldActivity
import alidoran.android.compose.developer_android_samples.courses.c1.TaskListActivity
import alidoran.android.compose.developer_android_samples.courses.c2.DeepToLazyActivity
import alidoran.android.compose.developer_android_samples.tutorial.FirstTillThreeActivity
import alidoran.android.compose.developer_android_samples.tutorial.FourthActivity
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

    val activity = this
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
                startActivity(Intent(activity, FirstTillThreeActivity::class.java))
            }) { Text(text = "First till third tutorials") }

            Button(onClick = {
                startActivity(Intent(activity, FourthActivity::class.java))
            }) { Text(text = "Fourth tutorial") }

            Button(onClick = {
                startActivity(Intent(activity, FirstCourseActivity::class.java))
            }) { Text(text = "First Course") }

            Button(onClick = {
                startActivity(Intent(activity, FirstCourseAnimationActivity::class.java))
            }) { Text(text = "First Course animation Activity") }

            Button(onClick = {
                startActivity(Intent(activity, ScaffoldActivity::class.java))
            }) { Text(text = "Scaffold Activity") }

            Button(onClick = {
                startActivity(Intent(activity, CodeLabBasicLayoutActivity::class.java))
            }) { Text(text = "CodeLab basic Activity") }


            Button(onClick = {
                startActivity(Intent(activity, TaskListActivity::class.java))
            }) { Text(text = "TaskList Activity") }

            Button(onClick = {
                startActivity(Intent(activity, DeepToLazyActivity::class.java))
            }) { Text(text = "DeepToLazy Activity") }

        }
    }

    @Preview
    @Composable
    fun PagePreview() {
        SetButtons()
    }
}