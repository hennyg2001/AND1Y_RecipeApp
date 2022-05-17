package com.example.and_recipeapp.network;

import android.util.Log;

import com.example.and_recipeapp.entities.RecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class ServiceGenerator {

    private static RecipeApi recipeApi;

    public static RecipeApi getRecipeApi() {
        if (recipeApi == null) {
            recipeApi = new Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecipeApi.class);
        }
        return recipeApi;
    }

}
