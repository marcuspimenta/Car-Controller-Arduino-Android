package com.carcontroller.mobile.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.bluetooth.BluetoothSocket;
import android.content.Context;

import com.carcontroller.mobile.util.LogUtil;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * 01/11/2012 13:06:15 
 */
public class BluetoothComunication extends Thread {
	 
	private boolean run;
	
	private BluetoothSocket bluetoothSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public BluetoothComunication(Context context, BluetoothSocket bluetoothSocket){
		this.bluetoothSocket = bluetoothSocket;
		
		run = true;
	}
	
	@Override
	public void run() {
		 super.run();
		
		 try {
			 dataInputStream = new DataInputStream(bluetoothSocket.getInputStream());
			 dataOutputStream = new DataOutputStream(bluetoothSocket.getOutputStream());
			
			 
			 while (run) {
//				 if(dataInputStream.available() > 0){
//					 byte[] msg = new byte[dataInputStream.available()];
//					 dataInputStream.read(msg, 0, dataInputStream.available());
//					 
//					 String msgReceiver = new String(msg);
//				 }
			 }
		 }catch (IOException e) {
			 LogUtil.e(e.getMessage());
			 
			 stopComunication();
		 }
	}
	
	public boolean sendMessageByBluetooth(String buffer){
		try {
			if(dataOutputStream != null){
				dataOutputStream.writeChars(buffer);
				dataOutputStream.flush();
				return true;
			}else{
				return false;
			}
		} catch (IOException e) {
			LogUtil.e(e.getMessage());
			return false;
		}
	}
	
	 public void stopComunication(){ 
		try {
			run = false;
			
			if(bluetoothSocket != null){
				bluetoothSocket.close();
			}
			
			if(dataInputStream != null && dataOutputStream != null){
				dataInputStream.close();
				dataOutputStream.close();
				
				dataInputStream = null;
				dataOutputStream = null;
			}
		} catch (IOException e) {
			LogUtil.e(e.getMessage());
		}
	 }
	 
 }