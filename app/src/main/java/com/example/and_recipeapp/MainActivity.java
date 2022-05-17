package com.example.and_recipeapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.and_recipeapp.entities.Recipe;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_recipeapp.databinding.ActivityMainBinding;
import com.example.and_recipeapp.viewmodel.RecipeViewModel;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Launcher
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    Intent data = result.getData();

                    if(result.getResultCode() == RESULT_OK) {

                        String title = data.getStringExtra(CreateEditRecipeActivity.EXTRA_TITLE);
                        String cuisine = data.getStringExtra(CreateEditRecipeActivity.EXTRA_CUISINE);
                        int servings = data.getIntExtra(CreateEditRecipeActivity.EXTRA_SERVINGS, 1);
                        String method = data.getStringExtra(CreateEditRecipeActivity.EXTRA_METHOD);
                        int cooktime = data.getIntExtra(CreateEditRecipeActivity.EXTRA_COOKTIME, 1);

                        Recipe recipe = new Recipe(title, cuisine, cooktime, servings, method);
                        recipeViewModel.insert(recipe);
                        Toast.makeText(this, "Recipe created...", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Toast.makeText(this, "Recipe not saved...", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateEditRecipeActivity.class);
                mStartForResult.launch(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipeViewModel.getAllRecipes().observe(this, (recipes) -> {
            adapter.setRecipes(recipes);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recipeViewModel.delete(adapter.getRecipeAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new RecipeAdapter.OnItemListClicker() {
            @Override
            public void onItemClick(Recipe recipe) {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra(RecipeActivity.RECIPE_ID, recipe.getId());
                intent.putExtra(RecipeActivity.RECIPE_TITLE, recipe.getTitle());
                intent.putExtra(RecipeActivity.RECIPE_CUISINE, recipe.getCuisine());
                intent.putExtra(RecipeActivity.RECIPE_COOKTIME, recipe.getCookTime());
                intent.putExtra(RecipeActivity.RECIPE_SERVINGS, recipe.getServings());
                intent.putExtra(RecipeActivity.RECIPE_METHOD, recipe.getMethod());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}