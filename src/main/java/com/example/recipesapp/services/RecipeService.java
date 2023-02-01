package com.example.recipesapp.services;
import com.example.recipesapp.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeService {
    Integer createRecipes(Recipe recipe);

    Recipe editRecipe(Integer recipeId, Recipe recipe);

    Recipe getRecipesId(Integer recipeId);


    boolean deleteRecipe(Integer recipeId);

    void deleteAllRecipe();

    Collection<Recipe> getAllRecipe();

    Path createReport() throws IOException;
}



