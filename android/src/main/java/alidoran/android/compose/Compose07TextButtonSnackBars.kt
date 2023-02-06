package alidoran.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class Compose07TextButtonSnackBars : ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent {
            Column {
                TextButtonSnackBars()
            }
        }
    }

    @Composable
    fun TextButtonSnackBars() {
        Column {
            SnackbarShow()
            Text(text = "------------")
            ScaffoldShow()
        }
    }

    @Composable
    fun SnackbarShow() {
        Snackbar {
            Text(text = "Hi")
        }
    }

    @Composable
    fun ScaffoldShow() {
        val scaffoldState = rememberScaffoldState()
        var textFieldState by remember {
            mutableStateOf("")
        }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                TextField(value = textFieldState,
                    onValueChange = { textFieldState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "myLable")}
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                    }
                }) {
                    Text(text = "Clicked")
                }
            }
        }
    }

    @Preview
    @Composable
    fun LayoutDefaultPreview() {
        ScaffoldShow()
    }
}