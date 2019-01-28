package com.example.readysteadymunch;

public class Recipe {
    String ID;
    String title;
    String image;
    String usedIngredientCount;
    String missedIngredientCount;
    String likes;

    public Recipe(String ID, String title, String image, String usedIngredientCount, String missedIngredientCount, String likes) {
        this.ID = ID;
        this.title = title;
        this.image = image;
        this.usedIngredientCount = usedIngredientCount;
        this.missedIngredientCount = missedIngredientCount;
        this.likes = likes;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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

    public String getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(String usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public String getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(String missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
