package com.example.recipesapp.services;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;

public interface RecipeService {



    Recipe createRecipes(Recipe recipe);

    Recipe getRecipesId(Integer recipeId);

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer ingredientId);
}
