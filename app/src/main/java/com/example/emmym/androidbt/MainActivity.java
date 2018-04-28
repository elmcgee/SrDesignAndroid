package com.example.emmym.androidbt;

import android.annotation.SuppressLint;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    SurfaceView cameraPreview;
    TextView txtResult;
    int RequestCameraPermissionID = 1001;
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    @Override
    public void handleResult(Result result) {
        //Do something with the result here
        Log.e("Handler", result.getText()); //print scan results
        Log.e("Handler",result.getBarcodeFormat().toString()); //print the scan format (qrcode)
        //show the scanner result into a dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
        //if you would like to resume scanning, call this method below
        //mScannerView.resumeCameraPreview(this);
    }//end handleResult
    public void QrScanner(View view) {
        mScannerView = new ZXingScannerView(this); // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results
        mScannerView.startCamera(); // Start Camera
    }//end QrSCanner
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera(); //Stop camera on pause
    }// end onPause
//hi


}//end MainActivity

