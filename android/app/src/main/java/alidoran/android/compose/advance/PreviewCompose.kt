package alidoran.android.compose.advance

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComposeForPreviewScreen() {
    Column {
        Text(text = "Sample Ali Doran Text")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sample Ali Doran Button")
        }
        Text(text = "Sample Ali Doran Text")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sample Ali Doran Button")
        }
        Text(text = "Sample Ali Doran Text")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sample Ali Doran Button")
        }
    }
}

@Composable
@Preview
private fun SimplePreview(){
    ComposeForPreviewScreen()
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewWithPhoneUi(){
    ComposeForPreviewScreen()
}