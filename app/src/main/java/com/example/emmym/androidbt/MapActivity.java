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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MapActivity extends Activity {
   // ImageView personStar = findViewById(R.id.star_person);
    //TextView  personName = findViewById(R.id.name_person);
   private ImageView personStar;
    private float blueX,blueY,greenX,greenY;
    String exhibitOne = "east-01";
    String exhibitTwo ="thomas-home";
    private  ArrayList<Integer> figureArray  = new ArrayList();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        blueX = 0;
        blueY = 0;
        greenX = 0;
        greenY = 0;
        final ImageView blueBox = findViewById(R.id.blue_square);// exhibitOne
        final ImageView greenBox = findViewById(R.id.green_square);// exhibitTwo
        personStar = findViewById(R.id.person_star);

        //BlueBox touch
        final Handler blueHandle = new Handler();
        blueBox.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View  v, MotionEvent event) {
                setBlueX(blueBox.getX());
                setBlueY(blueBox.getY());
                Toast.makeText(getApplicationContext(), "Blue X: " + String.valueOf(getBlueX()) + " Blue Y: " + String.valueOf(getBlueY()),Toast.LENGTH_SHORT).show();
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
                setGreenX(greenBox.getX());
                setGreenY(greenBox.getY());
                Toast.makeText(getApplicationContext(), "Green X: " + String.valueOf(getGreenX()) + " Green Y: " + String.valueOf(getGreenY()),Toast.LENGTH_SHORT).show();
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

    }// oncreate
    public void updateMap(String userLocation, String username){
       // final TextView personName = findViewById(R.id.name_person);
       // Toast.makeText(getApplicationContext(),userLocation + " " + username ,Toast.LENGTH_SHORT).show();
        //personName.setText(username);
        if(userLocation.equals(exhibitOne)) {
            personStar.setX(getBlueX());
            personStar.setY(getBlueY());
        }
        else if(userLocation.equals(exhibitTwo)){
            personStar.setX(getGreenX());
            personStar.setY(getGreenY());
        }
    }// updateMap

    public float getBlueX() {
        return blueX;
    }

    public void setBlueX(float blueX) {
        this.blueX = blueX;
    }

    public float getBlueY() {
        return blueY;
    }

    public void setBlueY(float blueY) {
        this.blueY = blueY;
    }

    public float getGreenX() {
        return greenX;
    }

    public void setGreenX(float greenX) {
        this.greenX = greenX;
    }

    public float getGreenY() {
        return greenY;
    }

    public void setGreenY(float greenY) {
        this.greenY = greenY;
    }
}//MapActivity


