package com.example.and_recipeapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "cuisine")
    public String cuisine;

    @ColumnInfo(name = "cookTime")
    public int cookTime;

    @ColumnInfo(name = "servings")
    public int servings;

    @ColumnInfo(name = "method")
    public String method;

    public Recipe(String title, String cuisine, int cookTime, int servings, String method) {
        this.title = title;
        this.cuisine = cuisine;
        this.cookTime = cookTime;
        this.servings = servings;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getServings() {
        return servings;
    }

    public String getMethod() {
        return method;
    }

    public void setId(int id) {
        this.id = id;
    }

}
