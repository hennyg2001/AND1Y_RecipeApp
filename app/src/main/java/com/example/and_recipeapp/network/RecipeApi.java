package com.example.and_recipeapp.network;

import com.example.and_recipeapp.entities.IngredientResponse;
import com.example.and_recipeapp.entities.Recipe;
import com.example.and_recipeapp.entities.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RecipeApi {

    @GET("https://api.spoonacular.com/recipes/{title}")
    Call<RecipeResponse> getRecipe(@Path("title") String title);

    @GET("https://api.spoonacular.com/food/ingredients/{name}")
    Call<IngredientResponse> getIngredient(@Path("name") String name);

}
