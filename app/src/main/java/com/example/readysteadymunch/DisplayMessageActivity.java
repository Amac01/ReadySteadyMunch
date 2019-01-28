package com.example.readysteadymunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DisplayMessageActivity extends AppCompatActivity {
    public List<Recipe> recipe_list = new ArrayList<>(); // Variable to store our recipes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        TextView outputsTxt = findViewById(R.id.outputsTxt); // This will be removed
        outputsTxt.setText(message);

        /*
        TextUtils.SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(",");
        ArrayList<String> ingredientsToArray = splitter.setString(message);
        String ingredients = TextUtils.join("%2C", ingredientsToArray); // join %2C
        */

        //API call
        RequestQueue queue = Volley.newRequestQueue(this);
        String ingredientCall = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=1&ingredients=apples%2Cflour%2Ceggs";
        String url = ingredientCall ; //+ ingredients

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is:" + response.substring(0, 500));
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            System.out.println("Array length is " + jsonArray.length());
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject item = (JSONObject) jsonArray.get(i);
                                recipe_list.add(new Recipe(item.getString("id"), item.getString("title"), item.getString("image"), item.getString("usedIngredientCount"), item.getString("missedIngredientCount"), item.getString("likes")));
                                System.out.println(recipe_list.get(0).getImage());
                                recycle_view_setup(); // Sets up recycle view adapter
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
        // Pass the API call into a Recipe List

        queue.add(stringRequest);


    }
    protected void recycle_view_setup(){
        // Recycler View
        System.out.println(recipe_list.size());
        RecipeListAdapter recipe_list_adapter = new RecipeListAdapter(recipe_list); // Add the list
        RecyclerView recipe_view = findViewById(R.id.recipe_by_ingredient);
        recipe_view.setHasFixedSize(true);
        recipe_view.setLayoutManager(new LinearLayoutManager(this));
        recipe_view.setAdapter(recipe_list_adapter);
    }
}
