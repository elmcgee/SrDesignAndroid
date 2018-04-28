package com.example.emmym.androidbt;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public DatabaseReference eMachinesRef;
    //qr code scanner object
    private IntentIntegrator qrScan;
    //View Objects
    private Button buttonScan;

    private User localUser;

    private static final String TAG = "MainActivity";
    private FirebaseSnapshotObject currentSnapshot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eMachinesRef = FirebaseDatabase.getInstance().getReference("events/");
        eMachinesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseSnapshotObject tempSnapshot = dataSnapshot.getValue(FirebaseSnapshotObject.class);
                if(currentSnapshot != null && localUser != null) {
                    Map<String, ArrayList<String>> detectionResult = tempSnapshot.whoWasDetected(currentSnapshot);
                    if (detectionResult != null) {
                        Iterator it = detectionResult.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            for(String name: ((ArrayList<String>)pair.getValue())) {
                                if(name.equals(localUser.getName())) {
                                    Toast.makeText(getApplicationContext(), "Your face is detected at " + pair.getKey(), Toast.LENGTH_LONG).show();
                                }

                                Log.d(TAG, "Detected " + name + "'s face at " + pair.getKey() + ".");
                            }
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }
                } else if(localUser == null) {
                    Toast.makeText(getApplicationContext(), "Make sure you scan your QRCode", Toast.LENGTH_LONG).show();
                }
                currentSnapshot = tempSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_qrcode);
        //intializing scan object
        qrScan = new IntentIntegrator(this);
        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }

    //method for touch screen button
    public void onButtonTouchClick(View v){
        if(v.getId() == R.id.button_touchScreen){
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);

        }
    }

    //method for bluetooth button
    public void onBTClick (View v) {
        if (v.getId() == R.id.bt_button) {
            Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
                    localUser = new User(obj.getString("name"));
//                    textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
}//end MainActivity

