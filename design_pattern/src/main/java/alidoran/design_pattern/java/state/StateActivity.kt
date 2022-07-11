package alidoran.design_pattern.java.state

import alidoran.design_pattern.databinding.ActivityStateBinding
import alidoran.design_pattern.java.state.java.CanvasState
import alidoran.design_pattern.java.state.java.CanvasWithoutPattern
import alidoran.design_pattern.java.state.java.SelectionToolJavaState
import alidoran.design_pattern.java.state.java.ToolEnumWithoutPatternState
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StateActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent(binding)
    }

    private fun initEvent(binding: ActivityStateBinding) {

        binding.btnWithoutPatternJavaSelection.setOnClickListener{
            CanvasWithoutPattern(
                ToolEnumWithoutPatternState.SELECTION
            ).mouseDown()
        }

        binding.btnWithoutPatternJavaSelection.setOnLongClickListener {
            CanvasWithoutPattern(
                ToolEnumWithoutPatternState.SELECTION
            ).mouseUp()
            true
        }

        binding.btnStateJavaSelection.setOnClickListener{
            CanvasState(
                SelectionToolJavaState()
            ).mouseDown()


        }

        binding.btnStateJavaSelection.setOnLongClickListener {
            CanvasState(
                SelectionToolJavaState()
            ).mouseUp()
            true
        }
    }
}