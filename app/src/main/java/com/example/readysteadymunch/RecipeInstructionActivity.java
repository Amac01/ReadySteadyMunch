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
    public RecipeInstruction recipe_instructions = new RecipeInstruction();

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
        String complete_url = start_url + recipe_id + parameters;

        // Current API: https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/informationBulk?ids=835677&includeNutrition=true

        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            System.out.println("Array length is " + jsonArray.length()); // Debugging purposes
                            jsonToJava(jsonArray); // Handles wrapping into Java objects


                            /*

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject item = (JSONObject) jsonArray.get(i);
                            }
                            */

                            setUpViews(); // This sets up the views
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

    protected void setUpViews() {
        // Add code to setup the views
    }

    protected void jsonToJava(JSONArray json_array) {
        try {
            JSONObject item = (JSONObject) json_array.get(0);
            recipe_instructions.setTitle(item.getString("title")); // Set recipe title
            recipe_instructions.setImage(item.getString("image")); // Set recipe image
            recipe_instructions.setReadyInMinutes(item.getInt("readyInMinutes")); // Set recipe ready in minutes
            recipe_instructions.setVegetarian(item.getBoolean("vegetarian")); // Set recipe vegetarian
            recipe_instructions.setVegan(item.getBoolean("vegan")); // Set recipe vegan
            recipe_instructions.setGlutenFree(item.getBoolean("glutenFree")); // Set recipe glutenFree
            recipe_instructions.setDairyFree(item.getBoolean("dairyFree")); // Set recipe dairyFree
            recipe_instructions.setServings(item.getInt("servings")); // Set recipe servings

            JSONArray ingredients_array = item.getJSONArray("extendedIngredients"); // Returns ingredients array
            // Adds ingredients to recipe_instructions arraylist
            for (int i = 0; i < item.length(); i++){
                JSONObject individual_ingredient = (JSONObject) ingredients_array.get(i);
                String ingredient = individual_ingredient.getString("originalString");
                recipe_instructions.addIngredients(ingredient);
            }

            // Adds instructions to recipe_instructions arraylist

            JSONArray analysedInstructions = item.getJSONArray("analyzedIngredients");
            JSONObject name_and_steps = (JSONObject) analysedInstructions.get(0);
            JSONArray steps = name_and_steps.getJSONArray("steps");

            for (int i = 0; i < item.length(); i++){
                JSONObject individual_instruction = (JSONObject) steps.get(0);
                String instruction = individual_instruction.getString("step");
                recipe_instructions.addInstructions(instruction);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
