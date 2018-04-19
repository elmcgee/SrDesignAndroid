package com.example.emmym.androidbt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
private String LOG_TAG;
private int REQUEST_ENABLE_BT = 99;
private BluetoothAdapter mBluetoothAdapter;
private Button button_enableBT, button_displayPairedBT, button_scanBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LOG_TAG = getResources().getString(R.string.app_name);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        button_enableBT = findViewById(R.id.button_enableBT);
        button_displayPairedBT = findViewById(R.id.button_alreadyPairedBT);
        button_scanBT = findViewById(R.id.button_scanBT);
        button_enableBT.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               enableBluetoothOnDevice();
            }
        });
        button_displayPairedBT.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                if (getArrayOfAlreadyPairedBluetoothDevices() != null)
                {
                    Intent intent = new Intent(MainActivity.this, AlreadyPairedList.class);
                    intent.putParcelableArrayListExtra("arrayOfPairedDevices", getArrayOfAlreadyPairedBluetoothDevices());
                    startActivity(intent);
                }
            }
        });
        button_scanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanForBluetoothDevices();
            }
        });

    }
    //method for touch screen button
    public void onButtonTouchClick(View v){
        if(v.getId() == R.id.button_touchScreen){
            Intent intent = new Intent(MainActivity.this, TouchScreenActivity.class);
            startActivity(intent);

        }
    }
    //method for navbar button
    public void onButtonNavClick(View v){
        if(v.getId() == R.id.button_navBar){
            Intent intent = new Intent(MainActivity.this, NavbarActivity.class);
            startActivity(intent);
        }
    }

    private void enableBluetoothOnDevice(){
        if(mBluetoothAdapter == null){
            Log.e(LOG_TAG, "This device does not have a bluetooth adapter");
            finish();
            //if the android device does no have bluetooth, just return and get out.
        }
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode == 0){
                //if resultcode is 0, the user selected "No" when propt to all the app to enable bluetooth
                Toast.makeText(this, "The user decided to deny bluetooth access", Toast.LENGTH_LONG);
            }
            else{
                Log.i(LOG_TAG,"User allowed bluetooth access!");
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private ArrayList getArrayOfAlreadyPairedBluetoothDevices(){
        ArrayList<BluetoothObject> arrayOfAlreadyPairedBTDevices = null;
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0){
            arrayOfAlreadyPairedBTDevices = new ArrayList<BluetoothObject>();
            //Loop through paired devices
            for(BluetoothDevice device: pairedDevices){
                BluetoothObject bluetoothObject = new BluetoothObject();
                bluetoothObject.setBluetooth_name(device.getName());
                bluetoothObject.setBluetooth_address(device.getAddress());
                bluetoothObject.setBluetooth_state(device.getBondState());
                bluetoothObject.setBluetooth_type(device.getType());
                bluetoothObject.setBluetooth_uuids(device.getUuids());
            arrayOfAlreadyPairedBTDevices.add(bluetoothObject);
            }
        }
        return arrayOfAlreadyPairedBTDevices;
    }
    private void scanForBluetoothDevices(){
        //Register the BroadcastReceiver
        Intent intent = new Intent(this, FoundBTDevices.class);
        startActivity(intent);
    }

}

