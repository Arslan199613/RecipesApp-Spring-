package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer recipeId = 1;
    @Override
    public Recipe createRecipes(@NonNull Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        recipeId++;
        return recipe;
    }
    @Override
    public Recipe getRecipesId(@NonNull Integer recipeId) {
        return recipeMap.get(recipeId);
    }
}
