package com.example.emmym.androidbt;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
// List the discovered bluetooth devices on an activity
public class FoundBTDevices extends ListActivity{
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<BluetoothObject> arrayOfFoundBTDevices;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        displayListOfFoundDevices();
    }
    private void displayListOfFoundDevices(){
        arrayOfFoundBTDevices = new ArrayList<BluetoothObject>();

        //start looking for bluetooth devices
        mBluetoothAdapter.startDiscovery();

        //Discover new devices and Create a BroadcastReceiver for ACTION_FOUND
        final BroadcastReceiver mReceiver = new BroadcastReceiver(){

            //  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                //When discovery finds a device
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    //Get the bluetoothDevice object from intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);

                    //Create the device oject and add it to the arrayList of devices

                    BluetoothObject btObject = new BluetoothObject();
                    btObject.setBluetooth_name(device.getName());
                    btObject.setBluetooth_address(device.getAddress());
                    btObject.setBluetooth_state(device.getBondState());
                    btObject.setBluetooth_type(device.getType());
                    btObject.setBluetooth_uuids(device.getUuids());
                    btObject.setBluetooth_rssi(rssi);

                    FoundBTDevicesAdapter adapter = new FoundBTDevicesAdapter(getApplicationContext(), arrayOfFoundBTDevices);

                    setListAdapter(adapter);
                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBluetoothAdapter.cancelDiscovery();
    }

}
