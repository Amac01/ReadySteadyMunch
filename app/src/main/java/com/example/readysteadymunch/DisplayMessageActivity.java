package com.example.readysteadymunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        //get the Intent that started this activity and extract the string
          Intent intent = getIntent();
          String message = intent.getStringExtra("EXTRA_MESSAGE");
//
//        // Capture the layout's TextView and set the string as its text
          TextView textView = findViewById(R.id.ingredientsTxt);

        TextView outputsTxt = findViewById(R.id.outputsTxt);
        outputsTxt.setText(message);
    }
}
