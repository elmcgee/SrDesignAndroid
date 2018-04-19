package com.example.emmym.androidbt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TouchScreenActivity extends Activity {
    private RelativeLayout touchScreen = null;
    private ImageView blueBox, greenBox, redBox, orangeBox, bigOrangeBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_screen);
        touchScreen = findViewById(R.id.touchLayout);
        blueBox = findViewById(R.id.blue_square);
        greenBox = findViewById(R.id.green_square);
        redBox = findViewById(R.id.red_square);
        orangeBox = findViewById(R.id.orange_square);
        bigOrangeBox = findViewById(R.id.orangeBack);

        //Layout touch
        touchScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "Johnny's got wiggly horns...you touched the layout", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //BlueBox touch
        blueBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "FACT: When a Blue Whale exhales, the spray from its blowhole shoots up to 30 feet.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //RedBox touch
        redBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"Ketchup's name may have derived from 'koe-chiap' or 'kei-tsap', a dilect from China, which means brine of pickled fish ", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //BlueBox touch
        greenBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "Fact: Peanuts are not nuts, they are seeds", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //RedBox touch
        orangeBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"Fact: Russia is the only country to have 9 timezones ", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //RedBox touch
        bigOrangeBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"Hey this is the background! Fact: Google's original name was BackRub", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

}
