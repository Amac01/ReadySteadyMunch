package com.example.readysteadymunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Displays error msg if ingredients entered return no recipes
        get intent
        if value == intent then display toast
        otherwise continue as normal

         */

        Intent intent = getIntent();
        if (intent != null && (intent.getStringExtra("wrong_params")) == "wrong_params") {
            Toast.makeText(getApplicationContext(), "No Recipes found",Toast.LENGTH_LONG).show();
        } else if (intent != null && (intent.getStringExtra("empty_response")) == "empty_response") {
            Toast.makeText(getApplicationContext(), "Enter ingredients separated by ','",Toast.LENGTH_LONG).show();
        }


    }

    public void sendMessage (View view) {
        EditText editText = findViewById(R.id.ingredientsTxt);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);
    }
}
