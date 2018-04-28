package com.example.emmym.androidbt;

import android.annotation.SuppressLint;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map); //displays contents of activity_map.xml
        // Image view of each of the "exhibits"; IDs located in activity.map.xml
        final ImageView blueBox = findViewById(R.id.blue_square);
        final ImageView greenBox = findViewById(R.id.green_square);
        final ImageView redBox = findViewById(R.id.red_square);
        final ImageView orangeBox = findViewById(R.id.orange_square);
        //BlueBox touch
        final Handler blueHandle = new Handler();
        blueBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View  v, MotionEvent event) {
                //  Toast.makeText(getApplicationContext(),getBlue_text(), Toast.LENGTH_SHORT).show(); // enables toast to appear when box is clicked
                if (event.getAction() == MotionEvent.ACTION_DOWN) { // if blue box has been touched once
                    blueHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent blue = new Intent(MainActivity.this, ActivityBlue.class); // grabs content from ActivityBlue.java
                            startActivity(blue);// starts activity
                            v.setPressed(false);// blue box is not pressed more than once. Only display new activity once
                        }
                    }, 900); //delay 900 ms
                    v.setPressed(true);

                    return true;
                }
                return false;
            }});
        //the following performs similar actions
        //RedBox touch
        final Handler redHandle = new Handler();
        redBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                // Toast.makeText(getApplicationContext(),getRed_text(), Toast.LENGTH_SHORT).show();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    redHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent red = new Intent(MainActivity.this, ActivityRed.class);
                            startActivity(red);
                            v.setPressed(false);
                        }
                    }, 900); //delays for 900 ms
                    v.setPressed(true);

                    return true;
                }
                return false;
            }
        });

        //GreenBox touch
        final Handler greenHandle = new Handler();
        greenBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                // Toast.makeText(getApplicationContext(), getGreen_text(), Toast.LENGTH_SHORT).show();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    greenHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent green = new Intent(MainActivity.this, ActivityGreen.class);
                            startActivity(green);
                            v.setPressed(false);
                        }
                    }, 900); //delay of 900 ms
                    v.setPressed(true);

                    return true;
                }
                return false;
            }
        });

        //orangeBox touch
        final Handler orangeHandle = new Handler();
        orangeBox.setOnTouchListener(new View.OnTouchListener() {

        eMachinesRef = FirebaseDatabase.getInstance().getReference("events/");
        eMachinesRef.addValueEventListener(new ValueEventListener() {

                @Override
                public boolean onTouch ( final View v, MotionEvent event){
                    // Toast.makeText(getApplicationContext(),getOrange_text(), Toast.LENGTH_SHORT).show();
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        orangeHandle.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent orange = new Intent(MainActivity.this, ActivityOrange.class);
                                startActivity(orange);
                                v.setPressed(false);
                            }

                        }, 900); //delay for 900 ms
                        v.setPressed(true);

                    }
                }
                currentSnapshot = tempSnapshot;
            }

                    return true;
                }
                return false;
            }
        });
    } //end onCreate

}//end MainActivity

