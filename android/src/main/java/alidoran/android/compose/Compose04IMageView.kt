package alidoran.android.compose

import alidoran.android.compose.ui.ui.theme.KotlinReferencesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Compose04IMageView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeImageView()
        }
    }

    @Composable
    fun ComposeImageView(){
        val painter = painterResource(id = alidoran.android.R.drawable.ic_launcher_background)
        val description = "My description"
        val title = "My Title"
        MyImageCard(painter = painter, contentDescription = description, title = title)
    }

    @Composable
    fun MyImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(0.5f),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    ),
                    contentAlignment = Alignment.BottomStart,
                ){
                    Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp),
                        modifier = Modifier
                            .padding(12.dp))
                }
            }
        }
    }

    @Preview
    @Composable
    fun LayoutDefaultPreview(){
        KotlinReferencesTheme {
        }
    }
}

