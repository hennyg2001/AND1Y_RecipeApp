package com.example.and_recipeapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.and_recipeapp.entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    LiveData<Recipe> getRecipe(int id);

    @Query("DELETE FROM recipe_table")
    void deleteAllRecipes();

    @Query("SELECT * FROM recipe_table ORDER BY title DESC")
    LiveData<List<Recipe>> getAllRecipes();

}
