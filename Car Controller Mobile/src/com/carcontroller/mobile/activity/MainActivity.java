package com.carcontroller.mobile.activity;

import android.app.Activity;
import android.os.Bundle;

import com.carcontroller.mobile.R;
import com.carcontroller.mobile.util.OrientationUtil;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 09:28:10 02/11/2013
 */
public class MainActivity extends Activity {
	
	private OrientationUtil accelerometerUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		accelerometerUtil = new OrientationUtil(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		accelerometerUtil.startSensor();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		accelerometerUtil.stopSensor();
	}

}