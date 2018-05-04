package com.example.emmym.androidbt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
// This wasn't used for the project, but may be put to good use for the next development of this project
// This allows you to alter the content of each of the exhibits. Can use this activity for admin
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

    // Edit the texts on the toast messages when you touch an exhibit
    public void setEditTexts() {
        // recieve the exhibits' textviews
        EditText blueEdit = findViewById(R.id.edit_blue);
        EditText greenEdit = findViewById(R.id.edit_green);
        // switch from altermap activity page to map activity page
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
               startActivity(intent);
    }
}
