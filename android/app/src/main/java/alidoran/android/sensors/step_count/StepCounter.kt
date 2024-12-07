import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

context(Context)
class StepCounter {
    private val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    fun stepFlow(): Flow<String> = callbackFlow {
        if (sensor == null) {
            trySend("Step counter sensor not available")
        }

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null) return
                trySend(event.values[0].toString())
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // No-op for this implementation
            }
        }

        val success = sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        if (!success) {
            trySend("Step counter sensor not available")
        }

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }
}