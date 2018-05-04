package com.example.emmym.androidbt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MapActivity extends Activity {
    public DatabaseReference eMachinesRef;
    private FirebaseSnapshotObject currentSnapshot;
    private User localUser;
    private float blueX,blueY,greenX,greenY;
    private ImageView personStar;
    String exhibitOne = "east-01";
    String exhibitTwo ="thomas-home";
    private static final String TAG = "MapActivity";

    interface MyHandlerInterface {
        void onHandle(String string);
    }
    class MyClass {
        MyHandlerInterface myHandler;
        public void setHandlerListener(MyHandlerInterface listener)
        {
            myHandler=listener;
        }
        protected void myEventFired(String string)
        {
            if(myHandler!=null)
                myHandler.onHandle(string);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.localUser = new User(getIntent().getStringExtra("localUser"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        blueX = 0;
        blueY = 0;
        greenX = 0;
        greenY = 0;
        final ImageView blueBox = findViewById(R.id.blue_square);// exhibitOne
        final ImageView greenBox = findViewById(R.id.green_square);// exhibitTwo
        final ImageView personStar = findViewById(R.id.person_star);

        personStar.setVisibility(View.INVISIBLE);

        final MyClass myListener = new MyClass();

        myListener.setHandlerListener(new MyHandlerInterface() {
            @Override
            public void onHandle(String string) {
                if(string.equals(exhibitOne)) {
                    personStar.setX(blueBox.getX());
                    personStar.setY(blueBox.getY());
                }
                else if(string.equals(exhibitTwo)){
                    personStar.setX(greenBox.getX());
                    personStar.setY(greenBox.getY());
                }
                personStar.setVisibility(View.VISIBLE);
            }// updateMap
        });

        //BlueBox touch
        // Handler handles the bluetooth activity to only display once per touching the exhibit on the map
        final Handler blueHandle = new Handler();
        blueBox.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View  v, MotionEvent event) {
                // Debug to find the x and y coordinates of the exhibit
              //  Toast.makeText(getApplicationContext(), "Blue X: " + String.valueOf(getBlueX()) + " Blue Y: " + String.valueOf(getBlueY()),Toast.LENGTH_SHORT).show();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    blueHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent blue = new Intent(MapActivity.this, ActivityBlue.class);
                            startActivity(blue);
                            v.setPressed(false);
                        }
                    }, 900);
                    v.setPressed(true);

                    return true;
                }
                return false;
            }});
        //GreenBox touch
        // Handler handles the bluetooth activity to only display once per touching the exhibit on the map
        final Handler greenHandle = new Handler();
        greenBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                // Debug to identify the location of the exhibit
              //  Toast.makeText(getApplicationContext(), "Green X: " + String.valueOf(getGreenX()) + " Green Y: " + String.valueOf(getGreenY()),Toast.LENGTH_SHORT).show();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    greenHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent green = new Intent(MapActivity.this, ActivityGreen.class);
                            startActivity(green);
                            v.setPressed(false);
                        }
                    }, 900);
                    v.setPressed(true);

                    return true;
                }
                return false;
            }
        });

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
                                    myListener.myEventFired(pair.getKey().toString());
                                    // updateMap(pair.getKey().toString(),name);
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

    }// oncreate
}//MapActivity


