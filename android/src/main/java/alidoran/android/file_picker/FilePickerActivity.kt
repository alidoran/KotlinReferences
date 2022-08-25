package alidoran.android.file_picker

import alidoran.android.databinding.ActivityFilePickerBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FilePickerActivity : AppCompatActivity() {

    lateinit var binding : ActivityFilePickerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}