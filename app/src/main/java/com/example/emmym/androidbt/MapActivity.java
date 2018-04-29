package com.example.emmym.androidbt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MapActivity extends Activity {
    private String blue_text = null;
    private String green_text = null;
    private String red_text = null;
    private String orange_text = null;
    private String orange_floor = null;
    private Toast toast;
    private  ArrayList<Integer> figureArray  = new ArrayList();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final ImageView blueBox = findViewById(R.id.blue_square);
        final ImageView greenBox = findViewById(R.id.green_square);
        final ImageView personStar = findViewById(R.id.star_person);


        //BlueBox touch
        final Handler blueHandle = new Handler();
        blueBox.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View  v, MotionEvent event) {
                float blueX = blueBox.getX();
                float blueY = blueBox.getY();
                Toast.makeText(getApplicationContext(), "Blue X: " + String.valueOf(blueX) + " Blue Y: " + String.valueOf(blueY),Toast.LENGTH_SHORT).show();
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
        final Handler greenHandle = new Handler();
        greenBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                float greenX = greenBox.getX();
                float greenY = greenBox.getY();
                Toast.makeText(getApplicationContext(), "Green X: " + String.valueOf(greenX) + " Green Y: " + String.valueOf(greenY),Toast.LENGTH_SHORT).show();
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

    }
}


