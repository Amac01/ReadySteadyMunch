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
        Intent intent = getIntent();
        if (intent.getStringExtra("empty_response") != null) {
            System.out.println(intent.getStringExtra("empty_response"));
            Toast.makeText(MainActivity.this, "No recipes found for your choices. Try again", Toast.LENGTH_LONG).show();
        }
    }

    public void sendMessage (View view){
        EditText editText = findViewById(R.id.ingredientsTxt);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);
    }
}

