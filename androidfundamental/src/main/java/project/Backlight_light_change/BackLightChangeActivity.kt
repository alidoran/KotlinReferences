package project.Backlight_light_change

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import alidoran.android_fundamental.R

class BackLightChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_light_change)

        val seekBar = findViewById<SeekBar>(R.id.seekBar_backlight_change)

//        val sensorManager: SensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val layoutParams = window.attributes
                window.attributes = layoutParams

                val percent = p1/100f
                layoutParams.screenBrightness = percent

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



    }
}