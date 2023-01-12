package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private Integer recipeId = 1;
    private Integer ingredientId = 1;


    @Override
    public Recipe createRecipes(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        recipeId++;
        return recipe;
    }
    @Override

    public Recipe getRecipesId(Integer recipeId) {
        return recipeMap.get(recipeId);
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMap.put(ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer ingredientId) {
        return ingredientMap.get(ingredientId);
    }
}
