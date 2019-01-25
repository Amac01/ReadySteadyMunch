package com.example.readysteadymunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Intent intent = getIntent();
//
//        String message = intent.getStringExtra(EXTRA_MESSAGE);
//        TextView textView = findViewById(R.id.addIngredients);
//        textView.setText(message);


//        final Button DisplayMessageActivityBtn = (Button)findViewById(R.id.DisplayMessageActivityBtn);
//        DisplayMessageActivityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent startIntent = new Intent(getApplicationContext(), DisplayMessageActivity.class);
//            }
//        });

    }


      public void sendMessage(View view){
          EditText editText = findViewById(R.id.ingredientsTxt);
          String message = editText.getText().toString();
          Intent intent = new Intent(this, DisplayMessageActivity.class);
          intent.putExtra("EXTRA_MESSAGE", message);
          startActivity(intent);


      }

}
