import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector(private val context: Context) : SensorEventListener {
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var lastAcceleration = 0f
    private var acceleration = 0f
    private var lastTime: Long = 0

    private var onShakeListener: (() -> Unit)? = null

    init {
        start()
    }

    fun setOnShakeListener(listener: () -> Unit) {
        onShakeListener = listener
    }

    fun start() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val currentTime = System.currentTimeMillis()
            val timeDifference = currentTime - lastTime
            lastTime = currentTime

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            acceleration = sqrt(x * x + y * y + z * z)
            val delta = acceleration - lastAcceleration

            // Ajuste os valores abaixo para definir a sensibilidade do ShakeDetector
            if (delta > 12 && timeDifference < 200) {
                onShakeListener?.invoke()
            }

            lastAcceleration = acceleration
        }
    }
}
