package alidoran.android.compose.viewmodel_hosting_structure

import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class ViewModelHoistingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm by viewModels<ComposeViewModel>()
            KotlinReferencesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewModelHoistingScreen(vm)
                }
            }
        }
    }

    @Composable
    fun ViewModelHoistingScreen(
        viewModel: ComposeViewModel,//viewModel have to inject to the Compose
        modifier: Modifier = Modifier
    ) {
        val fakeApiString by viewModel.apiCall.collectAsStateWithLifecycle()
        val multiTextString by viewModel.multiTextRequest.collectAsStateWithLifecycle()
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFromViewModel(
                fakeApiString,
                multiTextString,
                { viewModel.fakeCAllApi() },
                { viewModel.callMultiString() },
                modifier
            )
        }
    }

    @Composable
    fun TextFromViewModel(
        fakeApiCall: String,
        multiTextResult: String,
        callApiString: () -> Unit,
        callMultiString: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Column {
            Button(onClick = callApiString) { Text(text = "Call Api") }
            Text(text = fakeApiCall)
            Button(onClick = callMultiString) { Text(text = "CAll Multi String") }
            Text(text = multiTextResult)
        }
    }
}