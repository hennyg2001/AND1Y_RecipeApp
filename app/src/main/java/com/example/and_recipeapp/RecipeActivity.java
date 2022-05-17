package com.example.and_recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.and_recipeapp.entities.Recipe;
import com.example.and_recipeapp.viewmodel.RecipeViewModel;

public class RecipeActivity extends AppCompatActivity {

    public static final String RECIPE_ID = "com.example.and_recipeapp.RECIPE_ID";
    public static final String RECIPE_TITLE = "com.example.and_recipeapp.RECIPE_TITLE";
    public static final String RECIPE_CUISINE = "com.example.and_recipeapp.RECIPE_CUISINE";
    public static final String RECIPE_SERVINGS = "com.example.and_recipeapp.RECIPE_SERVINGS";
    public static final String RECIPE_COOKTIME = "com.example.and_recipeapp.RECIPE_COOKTIME";
    public static final String RECIPE_METHOD = "com.example.and_recipeapp.RECIPE_METHOD";

    private TextView titleTextView, cuisineTextView, cookingTimeTextView, servingsTextView, methodTextView;
    private Button closeButton, editButton;

    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        titleTextView = findViewById(R.id.titleTv);
        cuisineTextView = findViewById(R.id.cuisineTv);
        cookingTimeTextView = findViewById(R.id.cookTimeTv);
        servingsTextView = findViewById(R.id.servingsTv);
        methodTextView = findViewById(R.id.methodTv);
        closeButton = findViewById(R.id.closeBtn);
        editButton = findViewById(R.id.editBtn);

        Intent intent = getIntent();

        titleTextView.setText(intent.getStringExtra(RECIPE_TITLE));
        cuisineTextView.setText(intent.getStringExtra(RECIPE_CUISINE));
        cookingTimeTextView.setText(String.valueOf(intent.getIntExtra(RECIPE_COOKTIME, 0)));
        servingsTextView.setText(String.valueOf(intent.getIntExtra(RECIPE_SERVINGS, 0)));
        methodTextView.setText(intent.getStringExtra(RECIPE_METHOD));

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

                        int id = data.getIntExtra(CreateEditRecipeActivity.EXTRA_ID, -1);

                        if(id == -1) {
                            Toast.makeText(this, "Recipe can't be updated...", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Recipe recipe = new Recipe(title, cuisine, cooktime, servings, method);
                        recipe.setId(id);
                        recipeViewModel.update(recipe);
                        Toast.makeText(this, "Recipe updated...", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Toast.makeText(this, "Recipe not saved...", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                finish();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                Intent it = new Intent(RecipeActivity.this, CreateEditRecipeActivity.class);
                it.putExtra(CreateEditRecipeActivity.EXTRA_ID, intent.getStringExtra(RECIPE_ID));
                it.putExtra(CreateEditRecipeActivity.EXTRA_TITLE, intent.getStringExtra(RECIPE_TITLE));
                it.putExtra(CreateEditRecipeActivity.EXTRA_CUISINE, intent.getStringExtra(RECIPE_CUISINE));
                it.putExtra(CreateEditRecipeActivity.EXTRA_COOKTIME, intent.getStringExtra(RECIPE_COOKTIME));
                it.putExtra(CreateEditRecipeActivity.EXTRA_SERVINGS, intent.getStringExtra(RECIPE_SERVINGS));
                it.putExtra(CreateEditRecipeActivity.EXTRA_METHOD, intent.getStringExtra(RECIPE_METHOD));
                mStartForResult.launch(it);

            }
        });

    }

}
