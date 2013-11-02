package com.carcontroller.mobile.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 09:14:13 02/11/2013
 */
@SuppressWarnings("deprecation")
public class OrientationUtil implements SensorEventListener {

	private Sensor orientation;
	private SensorManager sensorManager;

	public OrientationUtil(Context context) {
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}
	
	public void startSensor(){
		sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void stopSensor(){
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			//LogUtil.i("(X, Y, Z) (" + event.values[0] + "," + event.values[1] + "," + event.values[2] + ")");
			
			if(event.values[1] > 20){
				LogUtil.i("left");
				
			}else if(event.values[1] < -20){
				LogUtil.i("right");
				
			}else{
				LogUtil.i("direct");
				
			}
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

}