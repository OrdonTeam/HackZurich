package com.ordonteam.hackzurich.sensors
import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import groovy.transform.CompileStatic

@CompileStatic
class AccelerometerActivator implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ActiveAble activeAble

    AccelerometerActivator(Activity activity, ActiveAble activeAble) {
        this.activeAble = activeAble
        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onSensorChanged(SensorEvent event){
        double square = event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]
        if(square>800){
            activeAble.activate()
        }
    }

    @Override
    void onAccuracyChanged(Sensor sensor, int i) {

    }
}
