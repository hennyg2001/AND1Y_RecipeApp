package com.example.and_recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.and_recipeapp.entities.Recipe;
import com.example.and_recipeapp.viewmodel.RecipeViewModel;

public class CreateEditRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.and_recipeapp.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.and_recipeapp.EXTRA_TITLE";
    public static final String EXTRA_CUISINE = "com.example.and_recipeapp.EXTRA_CUISINE";
    public static final String EXTRA_SERVINGS = "com.example.and_recipeapp.EXTRA_SERVINGS";
    public static final String EXTRA_COOKTIME = "com.example.and_recipeapp.EXTRA_COOKTIME";
    public static final String EXTRA_METHOD = "com.example.and_recipeapp.EXTRA_METHOD";

    private EditText inputTitle, inputCuisine, inputMethod;
    private NumberPicker inputCookingTime, inputServings;
    private Button createButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        inputTitle = findViewById(R.id.title);
        inputCuisine = findViewById(R.id.cuisine);
        inputCookingTime = findViewById(R.id.cookTime);
        inputServings = findViewById(R.id.servings);
        inputMethod = findViewById(R.id.method);
        createButton = findViewById(R.id.createBtn);
        cancelButton = findViewById(R.id.cancelBtn);

        inputCookingTime.setMaxValue(60);
        inputCookingTime.setMinValue(1);

        inputServings.setMaxValue(20);
        inputServings.setMinValue(1);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {

            setTitle("Edit Recipe");
            inputTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            inputCuisine.setText(intent.getStringExtra(EXTRA_CUISINE));
            inputCookingTime.setValue(intent.getIntExtra(EXTRA_COOKTIME, 1));
            inputServings.setValue(intent.getIntExtra(EXTRA_SERVINGS, 1));
            inputMethod.setText(intent.getStringExtra(EXTRA_METHOD));

        } else {

            setTitle("Add Recipe");

        }

        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){

                saveRecipe();

            }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){

                finish();

            }

        });

    }

    private void saveRecipe() {

        String title = inputTitle.getText().toString().trim();
        String cuisine = inputCuisine.getText().toString().trim();
        int cookingTime = inputCookingTime.getValue();
        int servings = inputServings.getValue();
        String method = inputMethod.getText().toString().trim();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_CUISINE, cuisine);
        data.putExtra(EXTRA_SERVINGS, servings);
        data.putExtra(EXTRA_COOKTIME, cookingTime);
        data.putExtra(EXTRA_METHOD, method);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

}
