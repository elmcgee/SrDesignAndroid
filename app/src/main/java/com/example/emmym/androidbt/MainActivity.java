package com.example.emmym.androidbt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference eMachinesRef;
    private static final String TAG = "MainActivity";
    private FirebaseSnapshotObject currentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eMachinesRef = FirebaseDatabase.getInstance().getReference("events/");
        eMachinesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseSnapshotObject tempSnapshot = dataSnapshot.getValue(FirebaseSnapshotObject.class);
                if(currentSnapshot != null) {
                    Map<String, ArrayList<String>> detectionResult = tempSnapshot.whoWasDetected(currentSnapshot);
                    if (detectionResult != null) {
                        Iterator it = detectionResult.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            for(String name: ((ArrayList<String>)pair.getValue())) {
                                Log.d(TAG, "Detected " + name + "'s face at " + pair.getKey() + ".");
                            }
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }
                }
                currentSnapshot = tempSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        setContentView(R.layout.activity_main);
    }

    //method for touch screen button
    public void onButtonTouchClick(View v){
        if(v.getId() == R.id.button_touchScreen){
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);

        }
    }
    
    //method for bluetooth button
    public void onBTClick (View v){
        if(v.getId() == R.id.bt_button){
            Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
    }
}

