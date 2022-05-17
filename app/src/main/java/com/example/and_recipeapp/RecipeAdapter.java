package com.example.and_recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_recipeapp.entities.Recipe;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private OnItemListClicker listener;
    private List<Recipe> recipes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        holder.title.setText(currentRecipe.getTitle());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public Recipe getRecipeAt(int position) {
        return recipes.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        private ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(recipes.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemListClicker {
        void onItemClick(Recipe recipe);
    }

    public void setOnItemClickListener(OnItemListClicker listener) {
        this.listener = listener;
    }

}
