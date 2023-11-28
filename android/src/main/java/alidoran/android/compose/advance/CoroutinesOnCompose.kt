package alidoran.android.compose.advance

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@Composable
fun CoroutinesOnCompose(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffectCompose {
            Log.d("Compose", "CoroutinesOnCompose: onTimeOut")
        }
        ScopeCompose()
    }
}

@Composable
fun ScopeCompose() {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            Log.d("Compose", "CoroutinesOnCompose: onTimeOut")
        }
    }) {

    }
}

@Composable
fun LaunchedEffectCompose(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)
    // LaunchedEffect(onTimeout){ is wrong
    LaunchedEffect(Unit) {
        delay(TimeUnit.SECONDS.toMillis(3))
        currentOnTimeout()
    }
}