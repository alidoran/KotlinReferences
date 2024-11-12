package alidoran.android.compose.developer_android_samples.courses.c1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.commonlibrary.fake_endpoint.generateCheckList

class TaskListActivity : ComponentActivity() {
//    Note: This activity needs ViewModel to remember the close state after reconfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinReferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { TaskList() }
            }
        }
    }
}

@Composable
private fun TaskList(
    modifier: Modifier = Modifier,
) {
    val list = remember{generateCheckList(100).toMutableStateList()}
    LazyColumn(modifier = modifier) {
        items(list) {
            TaskItemStateful(
                modifier = Modifier.padding(8.dp),
                taskName = it.title,
                checked = it.isChecked,
                onCloseTask = {list.remove(it)}
            )
        }
    }
}

@Composable
private fun TaskItemStateful(
    taskName: String,
    checked: Boolean,
    onCloseTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    var checkedState by rememberSaveable { mutableStateOf(checked) }

    TaskItemStateless(
        taskName = taskName,
        checked = checkedState,
        onCheckChange = { isChecked -> checkedState = isChecked },
        onCloseClick = onCloseTask,
        modifier = modifier
    )
}

@Composable
private fun TaskItemStateless(
    taskName: String,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Text(modifier = Modifier.weight(1f), text = taskName)
        Checkbox(checked = checked, onCheckedChange = onCheckChange)
        IconButton(onClick = onCloseClick) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemStatelessPreview() {
    KotlinReferencesTheme { TaskList() }
}