package com.carcontroller.mobile.activity;

import android.app.Activity;
import android.os.Bundle;

import com.carcontroller.mobile.R;
import com.carcontroller.mobile.businesslogic.CarControllerBusinessLogic;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 09:28:10 02/11/2013
 */
public class CarControllerActivity extends Activity implements GenericActivity{
	
	private CarControllerBusinessLogic carControllerBusinessLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public void settingsAttributes() {
		carControllerBusinessLogic = new CarControllerBusinessLogic(this);
	}

	@Override
	public void settingsView() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		carControllerBusinessLogic.startSensor();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		carControllerBusinessLogic.stopSensor();
	}

}