package com.example.and_recipeapp.data;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_recipeapp.entities.Recipe;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.transform.Result;

public class RecipeRepository {

    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allRecipes;
    private MutableLiveData<List<Recipe>> searchedRecipes = new MutableLiveData<>();

    public RecipeRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDao = database.recipeDao();
        allRecipes = recipeDao.getAllRecipes();
    }

    public LiveData<Recipe> getRecipe(int id) {
        return recipeDao.getRecipe(id);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void insert(Recipe recipe) {
        new InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void delete(Recipe recipe) {
        new DeleteRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public void update(Recipe recipe) {
        new UpdateRecipeAsyncTask(recipeDao).execute(recipe);
    }

    public static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao recipeDao;

        private InsertRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insert(recipes[0]);
            return null;
        }

    }

    public static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao recipeDao;

        private DeleteRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.delete(recipes[0]);
            return null;
        }

    }

    public static class UpdateRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao recipeDao;

        private UpdateRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.update(recipes[0]);
            return null;
        }

    }

}
