package android.lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R


class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        Log.d("LifeCycle", "onCreate")
        initEvent()

    }

    private fun initEvent() {
        findViewById<Button>(R.id.btn_lifecycle_fragment_start).setOnClickListener {
            startFragment()
        }

        findViewById<Button>(R.id.btn_lifecycle_fragment_stop).setOnClickListener {
            stopFragment()
        }

    }

    override fun onStart() {
        Log.d("LifeCycle", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("LifeCycle", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("LifeCycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("LifeCycle", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("LifeCycle", "onDestroy")
        super.onDestroy()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_lifecycle, LifeCycleFragment::class.java, Bundle())
            .commit()
    }

    private fun stopFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_lifecycle)
        supportFragmentManager.beginTransaction()
            .remove(fragment!!)
            .commit()
    }
}