package com.example.and_recipeapp.entities;

public class IngredientResponse {

    private String name;

    public Ingredient getIngredient() {
        return new Ingredient(name);
    }

}
