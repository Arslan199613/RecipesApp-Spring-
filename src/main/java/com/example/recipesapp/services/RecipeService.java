package com.example.recipesapp.services;
import com.example.recipesapp.model.Recipe;

public interface RecipeService {
    Recipe createRecipes(Recipe recipe);

    Recipe editRecipe(Integer recipeId, Recipe recipe);

    Recipe getRecipesId(Integer recipeId);


    boolean deleteRecipe(Integer recipeId);

    void deleteAllRecipe();

    Recipe getAllRecipe();
}



