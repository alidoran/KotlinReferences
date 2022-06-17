package project.recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import alidoran.android_fundamental.R
import project.recycler_view.simple.SimpleRecyclerActivity

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        findViewById<Button>(R.id.btn_simple_recycler_java).setOnClickListener {
            val intent = Intent(this, SimpleRecyclerActivity::class.java)
            startActivity(intent)
        }


    }
}