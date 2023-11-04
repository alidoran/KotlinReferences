package alidoran.android.compose.elementary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

class Compose09ConstraintLayout :ComponentActivity(){
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent{
            MyConstraintLayout()
        }
    }

    @Composable
    fun MyConstraintLayout(){
        //todo https://www.youtube.com/watch?v=FBpiOAiseD0&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=9
    }
}