package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer recipeId = 1;

    @Override
    public Recipe createRecipes(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        recipeId++;
        return recipe;
    }

    @Override
    public Recipe editRecipe(Integer recipeId, Recipe recipe) {
        if (recipeMap.containsKey(recipeId)) {
            recipeMap.put(recipeId, recipe);
            return recipe;
        }
        return null;
    }
    @Override
    public Recipe getRecipesId(Integer recipeId) {
        return recipeMap.get(recipeId);
    }

    @Override
    public boolean deleteRecipe(Integer recipeId) {
        if (recipeMap.containsKey(recipeId)) {
            recipeMap.remove(recipeId);
            return true;
        }
        return false;
    }
    @Override
    public void deleteAllRecipe() {
        recipeMap = new TreeMap<>();
    }

    @Override
    public Recipe getAllRecipe() {
        for (Recipe recipe : recipeMap.values()) {
            return recipe;
        }
        return null;
    }
}

