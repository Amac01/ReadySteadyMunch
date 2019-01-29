package com.example.readysteadymunch;

import java.util.ArrayList;

public class RecipeInstruction {
    String title; // Done
    String image; // Done
    boolean isVegetarian; // Done
    boolean isVegan; // Done
    boolean isGlutenFree; // Done
    boolean isDairyFree; // Done
    int Servings; // Done
    ArrayList<String> ingredients = new ArrayList<>(); // Done
    ArrayList<String> instructions = new ArrayList<>(); // Done
    int readyInMinutes; // Done

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return isDairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        isDairyFree = dairyFree;
    }

    public int getServings() {
        return Servings;
    }

    public void setServings(int servings) {
        Servings = servings;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void addIngredients(String ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void addInstructions(String instruction) {
        instructions.add(instruction);
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
}
