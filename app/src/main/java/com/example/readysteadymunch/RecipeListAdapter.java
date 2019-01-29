package com.example.readysteadymunch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    public List<Recipe> recipe_list;

    public RecipeListAdapter(List<Recipe> recipe_list){this.recipe_list = recipe_list;}
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public ImageView recipeImage;
        public TextView usedIngredientCount;
        public TextView missedIngredientCount;
        public TextView recipeLikes;
        public ViewHolder(View v){
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            recipeTitle = (TextView) v.findViewById(R.id.recipe_title);
            recipeImage = (ImageView) v.findViewById(R.id.recipe_image);
            usedIngredientCount = (TextView) v.findViewById(R.id.usedIngredientCount);
            missedIngredientCount= (TextView) v.findViewById(R.id.missedIngredientCount);
            recipeLikes= (TextView) v.findViewById(R.id.recipeLikes);
        }
    }
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Recipe recipe = recipe_list.get(position);
        final String ID = recipe.getID();
        String likes = "Likes: " + recipe.getLikes();
        String missing_ingredients = "Missing ingredients: " + recipe.getMissedIngredientCount();
        String used_ingredients = "Used ingredients: " + recipe.getUsedIngredientCount();

        holder.recipeTitle.setText(recipe.getTitle());
        Picasso.get().load(recipe.getImage()).
                resize(340, 200)
                .into(holder.recipeImage);

        holder.usedIngredientCount.setText(used_ingredients);
        holder.missedIngredientCount.setText(missing_ingredients);
        holder.recipeLikes.setText(likes);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Call method for intent
                Intent intent = new Intent(view.getContext(), RecipeInstructionActivity.class);
                intent.putExtra("EXTRA_MESSAGE", ID);
                view.getContext().startActivity(intent);
            }
        });
    };

    @Override
    public int getItemCount() {
        return this.recipe_list.size();
    }
}
