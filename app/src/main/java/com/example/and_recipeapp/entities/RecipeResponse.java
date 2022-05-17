package com.example.and_recipeapp.entities;

import com.example.and_recipeapp.entities.Recipe;

public class RecipeResponse {

    private String title;
    private String cuisine;
    private int cookTime;
    private int servings;
    private String method;

    public Recipe getRecipe() {
        return new Recipe(title, cuisine, cookTime, servings, method);
    }

}
