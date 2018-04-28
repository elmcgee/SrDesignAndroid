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


        /*
        // Read from the database
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }
        };
        eMachinesRef.addChildEventListener(childEventListener);

        */
// /*
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
                //TODO: if there is any changes from the previous snapshot, we need to know
                //TODO: who was caught on camera and where the camera is located.
                // Log.d(TAG, dataSnapshot.getValue().toString());
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, value);
//                Log.d(TAG, "Value is: " + value);
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

