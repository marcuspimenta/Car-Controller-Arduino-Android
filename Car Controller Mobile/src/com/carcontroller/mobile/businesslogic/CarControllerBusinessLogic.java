package com.carcontroller.mobile.businesslogic;

import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnDirectionInClinationDeviceListener;
import com.carcontroller.mobile.util.OrientationUtil;

import android.content.Context;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 10:52:00 02/11/2013
 */
public class CarControllerBusinessLogic implements OnDirectionInClinationDeviceListener{
	
	private OrientationUtil orientationUtil;

	public CarControllerBusinessLogic(Context context){
		orientationUtil = new OrientationUtil(context, this);
	}
	
	public void startSensor(){
		orientationUtil.startSensor();
	}
	
	public void stopSensor(){
		orientationUtil.stopSensor();
	}

	@Override
	public void onDirectionInClinationDevice(int type) {
		// TODO Auto-generated method stub
	}
}