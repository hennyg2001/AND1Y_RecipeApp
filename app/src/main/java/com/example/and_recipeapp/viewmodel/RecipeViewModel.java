package com.example.and_recipeapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_recipeapp.data.RecipeDao;
import com.example.and_recipeapp.data.RecipeRepository;
import com.example.and_recipeapp.entities.Recipe;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private LiveData<List<Recipe>> allRecipes;
    private RecipeRepository repository;

    public RecipeViewModel(@NotNull Application app) {
        super(app);
        repository = new RecipeRepository(app);
        allRecipes = repository.getAllRecipes();
    }

    public LiveData<Recipe> get(int id) {
        return repository.getRecipe(id);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void insert(Recipe recipe) {
        repository.insert(recipe);
    }

    public void update(Recipe recipe) {
        repository.update(recipe);
    }

    public void delete(Recipe recipe) {
        repository.delete(recipe);
    }

}
