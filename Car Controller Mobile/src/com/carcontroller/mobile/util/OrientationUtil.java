package com.carcontroller.mobile.util;

import com.carcontroller.mobile.businesslogic.Constants;
import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnDirectionInClinationDeviceListener;

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
	
	private OnDirectionInClinationDeviceListener onDirectionInClinationDeviceListener;

	public OrientationUtil(Context context, OnDirectionInClinationDeviceListener onDirectionInClinationDeviceListener) {
		this.onDirectionInClinationDeviceListener = onDirectionInClinationDeviceListener;
		
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
				onDirectionInClinationDeviceListener.onDirectionInClinationDevice(Constants.LEFT);
				
			}else if(event.values[1] < -20){
				LogUtil.i("right");
				onDirectionInClinationDeviceListener.onDirectionInClinationDevice(Constants.RIGHT);
				
			}else{
				LogUtil.i("direct");
				onDirectionInClinationDeviceListener.onDirectionInClinationDevice(Constants.DIRECT);
			}
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

}