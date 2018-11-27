package com.example.android.pedoometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.app.job.JobScheduler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , SensorEventListener {

    val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val mSensor: Sensor? = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    var running = false ;
    var sensorManager:SensorManager? = null ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        running = true ;
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepsSensor==null) {
        Toast.makeText(this , "no steps counter sensor !!!!", Toast.LENGTH_SHORT).show()
        }else
        {
            sensorManager?.registerListener(this , stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()

        running= false ;
        sensorManager?.unregisterListener(this )

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {

        if (running) {
        stepsvalue.setText("" +event.values[0])
        }

       // var running = false ;
       /// var sensorManager:SensorManager? = null ;

      ///  override fun onCreate(savedInstanceState: Bundle?) {
      ///      super.onCreate(savedInstanceState)
         //   setContentView(R.layout.activity_main)
           /// sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        }


    }

}
