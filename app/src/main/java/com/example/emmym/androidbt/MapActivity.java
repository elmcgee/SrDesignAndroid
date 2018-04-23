package com.example.emmym.androidbt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MapActivity extends Activity {
    private String blue_text = null;
    private String green_text = null;
    private String red_text = null;
    private String orange_text = null;
    private String orange_floor = null;
    private Toast toast;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        RelativeLayout touchScreen = findViewById(R.id.touchLayout);
        ImageView blueBox = findViewById(R.id.blue_square);
        ImageView greenBox = findViewById(R.id.green_square);
        ImageView redBox = findViewById(R.id.red_square);
        ImageView orangeBox = findViewById(R.id.orange_square);
        ImageView bigOrangeBox = findViewById(R.id.orangeBack);
        blue_text = "FACT: When a Blue Whale exhales, the spray from its blowhole shoots up to 30 feet.";
        green_text ="Fact: Peanuts are not nuts, they are seeds";
        red_text = "Ketchup's name may have derived from 'koe-chiap' or 'kei-tsap', a dilect from China, which means brine of pickled fish " ;
        orange_text = "Fact: Russia is the only country to have 9 timezones ";
        orange_floor = "Hey this is the background! Fact: Google's original name was BackRub";
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            setBlue_text(bundle.getString("blueText"));
            setGreen_text(bundle.getString("greenText"));
            setRed_text(bundle.getString("redText"));
            setOrange_text(bundle.getString("orangeText"));
            setOrange_floor(bundle.getString("orangeFloorText"));
        }
        //Layout touch
        touchScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "You touched the layout", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        //BlueBox touch
        blueBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),getBlue_text(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //RedBox touch
        redBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),getRed_text(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //BlueBox touch
        greenBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), getGreen_text(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //RedBox touch
        orangeBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),getOrange_text(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //RedBox touch
        bigOrangeBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),getOrange_floor(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

public void onEditMapClick(View v){
        if(v.getId() == R.id.alter_map_btn){
            Intent intent = new Intent(MapActivity.this,AlterMapActivity.class);
            startActivity(intent);
        }
}

    public void setBlue_text(String blue_text) {
        this.blue_text = blue_text;
    }

    public void setGreen_text(String green_text) {
        this.green_text = green_text;
    }

    public void setRed_text(String red_text) {
        this.red_text = red_text;
    }

    public void setOrange_text(String orange_text) {
        this.orange_text = orange_text;
    }

    public void setOrange_floor(String orange_floor) {
        this.orange_floor = orange_floor;
    }

    public String getBlue_text() {
        return blue_text;
    }

    public String getGreen_text() {

        return green_text;
    }

    public String getRed_text() {
        return red_text;
    }

    public String getOrange_text() {
        return orange_text;
    }

    public String getOrange_floor() {
        return orange_floor;
    }
}
