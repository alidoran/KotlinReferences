package alidoran.android.compose.developer_tutorial

import alidoran.android.R
import alidoran.android.compose.ui.ui.theme.KotlinReferencesTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("SameParameterValue")
class FirstTillThreeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val msg = ComposeMessage("Ali", "Doran")
        setContent {
            Column {
                MessageCard("AliDoran")
                Divider()
                MessageCardModel(msg)
                Divider()
                ImageMessageCard(msg)
                Divider()
                ImageResizedMessageCard(msg)
                Divider()
                ThemeMessageCard(msg)
                Divider()
                ColoredMessageCard(msg)
                Divider()
                TypographyMessageCard(msg)
                Divider()
                ShapedMessageCard(msg)
            }
        }
    }

    @Composable
    private fun MessageCard(name: String) {
        Text("MessageCard: Hello $name",
            color = MaterialTheme.colorScheme.inversePrimary)
    }

    //Lesson 2
    @Composable
    private fun MessageCardModel(msg: ComposeMessage) {
        Column {
            Text(text = "MessageCardModel:", color = MaterialTheme.colorScheme.inversePrimary)
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }


    @Composable
    private fun ImageMessageCard(msg: ComposeMessage) {
        Row {
            Image(
                painter = painterResource(R.drawable.baseline_thumb_up_inverse_24dp),
                contentDescription = "thumb_up",
            )

            Column {
                Text(text = "ImageMessageCard:", color = MaterialTheme.colorScheme.inversePrimary)
                Text(text = msg.author)
                Text(text = msg.body)
            }

        }
    }

    @Composable
    private fun ImageResizedMessageCard(msg: ComposeMessage) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_thumb_up_inverse_24dp),
                contentDescription = "thumb_up",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .background(Color.Green)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = "ImageResizedMessageCard:", color = MaterialTheme.colorScheme.inversePrimary)
                Text(text = msg.author)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }

//    Lesson 3

    @Composable
    private fun ThemeMessageCard(msg: ComposeMessage) {
        KotlinReferencesTheme {
            Surface {
                Column {
                    Text(text = "Theme is set:")
                    ImageResizedMessageCard(msg)
                }
            }
        }
    }

    @Composable
    private fun ColoredMessageCard(msg: ComposeMessage) {
        Text(text = "ColoredMessageCard:", color = MaterialTheme.colorScheme.inversePrimary)
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_thumb_up_inverse_24dp),
                contentDescription = "thumb_up",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }

    @Composable
    private fun TypographyMessageCard(msg: ComposeMessage) {
        Text(text = "TypographyMessageCard:", color = MaterialTheme.colorScheme.inversePrimary)
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_thumb_up_inverse_24dp),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    @Composable
    private fun ShapedMessageCard(msg: ComposeMessage) {
        Text(text = "ShapedMessageCard:", color = MaterialTheme.colorScheme.inversePrimary)
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_thumb_up_inverse_24dp),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    //Lesson 4


    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    private fun PreviewHelloWorld() {
        val msg = ComposeMessage("Ali", "Doran")
        Column {
            MessageCard("AliDoran")
            Divider()
            MessageCardModel(msg)
            Divider()
            ImageMessageCard(msg)
            Divider()
            ImageResizedMessageCard(msg)
            Divider()
            ThemeMessageCard(msg)
            Divider()
            ColoredMessageCard(msg)
            Divider()
            TypographyMessageCard(msg)
            Divider()
            ShapedMessageCard(msg)
        }
    }
}