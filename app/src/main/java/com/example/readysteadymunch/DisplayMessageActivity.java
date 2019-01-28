package com.example.readysteadymunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        /*

        TextUtils.SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(',');
        String ingredientsToArray = message;
        String ingredients = (String) TextUtils.join("%2C", Collections.singleton(ingredientsToArray));
        splitter.setString(message);

        */

        //API call

        RequestQueue queue = Volley.newRequestQueue(this);
        String ingredientCall = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=1&ingredients=" + message;
        String url = ingredientCall;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is:" + response.substring(0, 950));
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            System.out.println("Array length is " + jsonArray.length());
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject item = (JSONObject) jsonArray.get(i);
                                String itemName = item.getString("title");
                                System.out.println(itemName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work");

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "");
                return headers;
            }
        };
        queue.add(stringRequest);
//        // Capture the layout's TextView and set the string as its text

        TextView outputsTxt = findViewById(R.id.outputsTxt);
        outputsTxt.setText(message);
    }
}
