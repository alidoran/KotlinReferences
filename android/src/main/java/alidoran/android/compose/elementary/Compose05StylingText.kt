package alidoran.android.compose.elementary

import alidoran.android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class Compose05StylingText: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            StylingText()
        }
    }

    @Composable
    fun StylingText(){
        val fontFamily = FontFamily(
            Font(R.font.kolker, FontWeight.Normal)
        )
        Column() {
            FirstLine(fontFamily = fontFamily)
            Text(text = "--------------")
            SecondLine(fontFamily= fontFamily)
        }

    }

    @Composable
    private fun FirstLine(fontFamily: FontFamily){
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF101010))){
            Text(
                text = "Styling text",
                color = Color.White,
                fontSize = 45.sp,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Thin,
                textDecoration = TextDecoration.Underline
            )
        }
    }

    @Composable
    private fun SecondLine(fontFamily: FontFamily) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF101010))){
            Text(
                text = buildAnnotatedString {
                                            withStyle(style = SpanStyle(
                                                color = Color.Green,
                                                fontSize = 50.sp
                                            )){
                                                append("S")
                                            }
                    append("tylish text")
                },
                color = Color.White,
                fontSize = 45.sp,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Thin,
                textDecoration = TextDecoration.Underline
            )
        }
    }

    @Preview
    @Composable
    fun LayoutDefaultPreview(){
        StylingText()
    }
}