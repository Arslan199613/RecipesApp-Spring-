package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static Integer recipeId = 0;

    @Override
    public Integer createRecipes(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        return recipeId++;
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

