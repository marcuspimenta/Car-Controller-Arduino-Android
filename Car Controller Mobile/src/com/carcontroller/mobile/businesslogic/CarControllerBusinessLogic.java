package com.carcontroller.mobile.businesslogic;

import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import com.carcontroller.mobile.R;
import com.carcontroller.mobile.alertdialog.AlertDialogDevicesFound;
import com.carcontroller.mobile.bluetooth.BluetoothComunication;
import com.carcontroller.mobile.bluetooth.BluetoothManager;
import com.carcontroller.mobile.bluetooth.EventsBluetoothReceiver;
import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnBluetoothDeviceSelectedListener;
import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnConnectionBluetoothListener;
import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnDirectionInClinationDeviceListener;
import com.carcontroller.mobile.businesslogic.IBusinessLogic.OnSearchBluetoothListener;
import com.carcontroller.mobile.task.BluetoothClientTask;
import com.carcontroller.mobile.util.OrientationUtil;
import com.carcontroller.mobile.util.ToastUtil;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 10:52:00 02/11/2013
 */
public class CarControllerBusinessLogic implements OnDirectionInClinationDeviceListener,
												   OnConnectionBluetoothListener,
												   OnBluetoothDeviceSelectedListener, 
												   OnSearchBluetoothListener{
	
	private Activity activity;
	
	private ToastUtil toastUtil;
	private OrientationUtil orientationUtil;

	private BluetoothManager bluetoothManager;
	private BluetoothComunication bluetoothComunication;
	private AlertDialogDevicesFound alertDialogDevicesFound;
	private EventsBluetoothReceiver eventsBluetoothReceiver;

	public CarControllerBusinessLogic(Activity activity){
		this.activity = activity;
		
		toastUtil = new ToastUtil(activity);
		orientationUtil = new OrientationUtil(activity, this);
		
		bluetoothManager = new BluetoothManager();
		alertDialogDevicesFound = new AlertDialogDevicesFound(activity, this);
		eventsBluetoothReceiver = new EventsBluetoothReceiver(activity, this);
	}
	
	public void inicializaBluetooth() {
		if (bluetoothManager.verifySuportedBluetooth()) {
			if (!bluetoothManager.isEnabledBluetooth()) { 
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); 
				activity.startActivityForResult(enableBtIntent, Constants.BT_ACTIVATE);
			}
		} else {
			toastUtil.showToast(activity.getString(R.string.no_support_bluetooth));
			activity.finish();
		}
	}
	
	public void startFoundDevices() {
		stopCommucanition();

		eventsBluetoothReceiver.showProgress();
		bluetoothManager.getBluetoothAdapter().startDiscovery();
	}
	
	public void stopCommucanition() {
		if (bluetoothComunication != null) {
			bluetoothComunication.stopComunication();
		}
	}
	
	public void startClient(BluetoothDevice bluetoothDevice) {
		BluetoothClientTask bluetoothClientTask = new BluetoothClientTask(activity, this);
		bluetoothClientTask.execute(bluetoothDevice);
	}
	
	public void starCommunication(BluetoothSocket bluetoothSocket) {
		bluetoothComunication = new BluetoothComunication(activity, bluetoothSocket);
		bluetoothComunication.start();
	}
	
	public boolean sendMessage(String buffer) {
		if (bluetoothComunication != null) {
			return bluetoothComunication.sendMessageByBluetooth(buffer);
		} else {
			return false;
		}
	}
	
	public void enableRegisterFilter(boolean enable) {
		if(enable){
			eventsBluetoothReceiver.registerFilters();
		}else{
			eventsBluetoothReceiver.unregisterFilters();
		}
	}
	
	public void enableSensor(boolean enable){
		if(enable){
			orientationUtil.startSensor();
		}else{
			orientationUtil.stopSensor();
		}
	}
	
	@Override
	public void onBluetoothDeviceSelected(BluetoothDevice bluetoothDevice) {
		startClient(bluetoothDevice);
	}

	@Override
	public void onConnectionBluetooth(BluetoothSocket bluetoothSocket) {
		starCommunication(bluetoothSocket);
	}

	@Override
	public void onSearchBluetooth(List<BluetoothDevice> devicesFound) {
		alertDialogDevicesFound.settingsAlertDialog(devicesFound);
	}
	
	@Override
	public void onDirectionInClinationDevice(int type) {
		switch (type) {
			case Constants.RIGHT:
				sendMessage("a");
				break;
			case Constants.LEFT:
				sendMessage("b");
				break;
		}
	}
}