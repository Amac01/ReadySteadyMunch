package com.example.readysteadymunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class RecipeInstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
        Intent intent = getIntent();
        String id = intent.getStringExtra("EXTRA_MESSAGE");
        System.out.println(id);

        // API Call
        RequestQueue queue = Volley.newRequestQueue(this);
        String start_url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/informationBulk?";
        String recipe_id = "ids=" + id;
        String parameters = "&includeNutrition=true";
        String complete_url= start_url + recipe_id + parameters;

        // Current API: https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/informationBulk?ids=835677&includeNutrition=true

        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            System.out.println("Array length is " + jsonArray.length());
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject item = (JSONObject) jsonArray.get(i);
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
    }
}
