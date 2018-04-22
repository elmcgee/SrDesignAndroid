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

    //method for bluetooth button
    public void onButtonBTClick(View v){
        if(v.getId() == R.id.button_BT){
            Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
    }

}

