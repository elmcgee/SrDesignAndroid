package com.example.emmym.androidbt;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method for touch screen button
    public void onButtonTouchClick(View v){
        if(v.getId() == R.id.button_touchScreen){
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);

        }
        //method for bluetooth button
    }
        public void onBTClick (View v){
            if(v.getId() == R.id.bt_button){
                Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
    }


}

