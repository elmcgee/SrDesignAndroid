package com.example.emmym.androidbt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altermap);
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditTexts();
            }
        });
    }//end onCreate

    public void setEditTexts() {

        EditText blueEdit = findViewById(R.id.edit_blue);
        EditText greenEdit = findViewById(R.id.edit_green);
        EditText redEdit = findViewById(R.id.edit_red);
        EditText orangeEdit = findViewById(R.id.edit_orange);
        EditText orangeFloorEdit = findViewById(R.id.edit_orange_floor);

        Intent intent = new Intent(AlterMapActivity.this, MapActivity.class);
        String blue_text = "FACT: When a Blue Whale exhales, the spray from its blowhole shoots up to 30 feet.";
        if ((blueEdit.getText().toString().trim().length()) > 0)
            intent.putExtra("blueText", blueEdit.getText().toString());
        else
            intent.putExtra("blueText", blue_text);
        String green_text = "Fact: Peanuts are not nuts, they are seeds";
        if ((greenEdit.getText().toString().trim().length()) > 0)
            intent.putExtra("greenText", greenEdit.getText().toString());
        else
            intent.putExtra("greenText", green_text);
        String red_text = "Ketchup's name may have derived from 'koe-chiap' or 'kei-tsap', a dilect from China, which means brine of pickled fish ";
        if ((redEdit.getText().toString().trim().length()) > 0)
            intent.putExtra("redText", redEdit.getText().toString());
        else
            intent.putExtra("redText", red_text);
        String orange_text = "Fact: Russia is the only country to have 9 timezones ";
        if ((orangeEdit.getText().toString().trim().length()) > 0)
            intent.putExtra("orangeText", orangeEdit.getText().toString());
        else
            intent.putExtra("orangeText", orange_text);
        String orange_floor = "Hey this is the background! Fact: Google's original name was BackRub";
        if ((orangeFloorEdit.getText().toString().trim().length()) > 0)
            intent.putExtra("orangeFloorText", orangeFloorEdit.getText().toString());
        else
            intent.putExtra("orangeFloorText", orange_floor);
        startActivity(intent);
    }
}
