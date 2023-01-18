package com.example.recipesapp.services;
import com.example.recipesapp.model.Ingredient;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredientId(Integer ingredientId);

    Ingredient editIngredient(Integer ingredientId, Ingredient ingredient);

    boolean deleteIngredient(Integer ingredientId);

    void deleteAllIngredient();

    Ingredient getAllIngredient();
}

