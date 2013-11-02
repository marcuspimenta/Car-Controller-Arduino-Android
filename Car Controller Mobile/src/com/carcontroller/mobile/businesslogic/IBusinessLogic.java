package com.carcontroller.mobile.businesslogic;

import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 10:45:51 02/11/2013
 */
public interface IBusinessLogic {

	public interface OnDirectionInClinationDeviceListener{ 
        public abstract void onDirectionInClinationDevice(int type); 
    }
	
	public interface OnSearchBluetoothListener{
		public abstract void onSearchBluetooth(List<BluetoothDevice> devicesFound);
	}

	public interface OnConnectionBluetoothListener{
		public abstract void onConnectionBluetooth(BluetoothSocket bluetoothSocket);
	}
	
	public interface OnBluetoothDeviceSelectedListener{
		public abstract void onBluetoothDeviceSelected(BluetoothDevice bluetoothDevice);
	}
	
	public interface OnMessageListener{
		public abstract void onMessage(String message);
	}
	
}