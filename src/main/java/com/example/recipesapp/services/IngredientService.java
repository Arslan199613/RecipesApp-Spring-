package com.example.recipesapp.services;
import com.example.recipesapp.model.Ingredient;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer ingredientId);
}

