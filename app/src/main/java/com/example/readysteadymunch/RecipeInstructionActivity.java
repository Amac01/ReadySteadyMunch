package com.example.readysteadymunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            jsonToJava(jsonArray);
                            setUpViews();
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
                headers.put("X-RapidAPI-Key", "706acb9b8fmsh417662e132c35d1p1347afjsn89cf66a51d20");
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    protected void setUpViews() {
        TextView recipe_title = findViewById(R.id.recipe_title);
        ImageView recipe_picture = findViewById(R.id.recipe_picture);
        TextView recipe_ingredients = findViewById(R.id.recipe_ingredients);
        TextView recipe_nutrition = findViewById(R.id.recipe_nutrition);
        TextView recipe_complete_instructions = findViewById(R.id.recipe_instructions);
        recipe_title.setText(recipe_instructions.getTitle());
        Picasso.get().load(recipe_instructions.getImage()).resize(340, 200).into(recipe_picture);
        String complete_ingredients = "";
        String complete_instructions = "";
        String complete_nutrition = "Ready in minutes: " + recipe_instructions.getReadyInMinutes() +
                " minutes\n" +
                "\nVegetarian: " + recipe_instructions.isVegetarian() +
                "\nVegan: " + recipe_instructions.isVegan() +
                "\nGluten Free: " + recipe_instructions.isGlutenFree() +
                "\nDairy Free: " + recipe_instructions.isDairyFree() +
                "\nServes: " + recipe_instructions.getServings();
        for (String element: recipe_instructions.getIngredients()){
            complete_ingredients += element + " \n";
        }
        for (String element: recipe_instructions.getInstructions()){
            complete_instructions += element + " \n";
        }
        System.out.println(complete_ingredients);
        System.out.println(complete_instructions);
        recipe_ingredients.setText(complete_ingredients);
        recipe_nutrition.setText(complete_nutrition);
        recipe_complete_instructions.setText(complete_instructions);
    }

    protected void jsonToJava(JSONArray json_array) {
        try {
            JSONObject item = (JSONObject) json_array.get(0);

            recipe_instructions.setTitle(item.getString("title"));
            recipe_instructions.setImage(item.getString("image"));
            recipe_instructions.setReadyInMinutes(item.getInt("readyInMinutes"));
            recipe_instructions.setVegetarian(item.getBoolean("vegetarian"));
            recipe_instructions.setVegan(item.getBoolean("vegan"));
            recipe_instructions.setGlutenFree(item.getBoolean("glutenFree"));
            recipe_instructions.setDairyFree(item.getBoolean("dairyFree"));
            recipe_instructions.setServings(item.getInt("servings"));

            JSONArray ingredients_array = item.getJSONArray("extendedIngredients");

            for (int i = 0; i < ingredients_array.length(); i++){
                JSONObject individual_ingredient = (JSONObject) ingredients_array.get(i);
                String ingredient = individual_ingredient.getString("originalString");
                recipe_instructions.addIngredients(ingredient);
            }



            JSONArray analysedInstructions = item.getJSONArray("analyzedInstructions");
            System.out.println("analysed instructions" + analysedInstructions);
            JSONObject name_and_steps = (JSONObject) analysedInstructions.get(0);
            System.out.println("name and steps" + name_and_steps);
            JSONArray steps = name_and_steps.getJSONArray("steps");
            System.out.println("Steps array:" + steps);

            for (int i = 0; i < steps.length(); i++){
                JSONObject individual_instruction = (JSONObject) steps.get(i);
                int instruction_number = individual_instruction.getInt("number");
                String instruction = individual_instruction.getString("step");
                System.out.println("Individual instruction number" + instruction_number);
                System.out.println("Individual instruction" + instruction);
                System.out.println("Place in loop:" + i);
                String name_and_instruction = instruction_number + ". " + instruction;
                recipe_instructions.addInstructions(name_and_instruction);
            }

            System.out.println(recipe_instructions.getTitle());
            System.out.println(recipe_instructions.getImage());
            System.out.println(recipe_instructions.getReadyInMinutes());
            System.out.println(recipe_instructions.isVegetarian());
            System.out.println(recipe_instructions.isVegan());
            System.out.println(recipe_instructions.isGlutenFree());
            System.out.println(recipe_instructions.isDairyFree());
            System.out.println(recipe_instructions.getServings());
            System.out.println(recipe_instructions.getIngredients());
            System.out.println(recipe_instructions.getInstructions());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
