package com.example.readysteadymunch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    public List<Recipe> recipe_list;

    public RecipeListAdapter(List<Recipe> recipe_list){this.recipe_list = recipe_list;}
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;
        public TextView usedIngredientCount;
        public TextView missedIngredientCount;
        public TextView recipeLikes;
        public ViewHolder(View v){
            super(v);
            recipeTitle = <>;
            usedIngredientCount = <>;
            missedIngredientCount= <>;
            recipeLikes= <>;
        }
    }
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Recipe recipe = recipe_list.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
        holder.usedIngredientCount.setText(recipe.getUsedIngredientCount());
        holder.missedIngredientCount.setText(recipe.getMissedIngredientCount());
        holder.recipeLikes.setText(recipe.getLikes());
    }

    @Override
    public int getItemCount() {
        return this.recipe_list.size();
    }
}
