package alidoran.android.activity_result_launcher

import alidoran.android.databinding.ActivityResultLauncherSourceBinding
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ActivityResultLauncherSource : AppCompatActivity() {

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            println(result.data?.getStringExtra("ali"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultLauncherSourceBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnNextPage.setOnClickListener {
            val intent = Intent(this, ActivityResultLauncherDest::class.java)
            resultLauncher.launch(intent)
        }
    }


}