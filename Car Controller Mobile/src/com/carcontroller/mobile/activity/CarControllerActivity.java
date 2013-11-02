package com.carcontroller.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.carcontroller.mobile.R;
import com.carcontroller.mobile.businesslogic.CarControllerBusinessLogic;
import com.carcontroller.mobile.businesslogic.Constants;
import com.carcontroller.mobile.util.ToastUtil;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 09:28:10 02/11/2013
 */
public class CarControllerActivity extends Activity implements GenericActivity{
	
	private ImageView imageViewOn;
	private ImageView imageViewOff;
	
	private ToastUtil toastUtil;
	private CarControllerBusinessLogic carControllerBusinessLogic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settingsAttributes();
		settingsView();
	}
	
	@Override
	public void settingsAttributes() {
		toastUtil = new ToastUtil(this);
		carControllerBusinessLogic = new CarControllerBusinessLogic(this);
		
		carControllerBusinessLogic.inicializaBluetooth();
		carControllerBusinessLogic.enableSensor(true);
		carControllerBusinessLogic.enableRegisterFilter(true);
	}

	@Override
	public void settingsView() {
		imageViewOn = (ImageView)findViewById(R.id.imageViewOn);
		imageViewOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				carControllerBusinessLogic.sendMessage(Constants.COMMAND_CAR_ON);
			}
		});
		
		imageViewOff = (ImageView)findViewById(R.id.imageViewOff);
		imageViewOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				carControllerBusinessLogic.sendMessage(Constants.COMMAND_CAR_OFF);
			}
		});
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_devices:
            	carControllerBusinessLogic.startFoundDevices();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		carControllerBusinessLogic.enableSensor(false);
		carControllerBusinessLogic.enableRegisterFilter(false);
		carControllerBusinessLogic.stopCommucanition();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
			case Constants.BT_ACTIVATE:
				if (RESULT_OK != resultCode) {
					toastUtil.showToast(getString(R.string.activate_bluetooth_to_continue));
					finish(); 
				}
				break;
		}
	}
	
}