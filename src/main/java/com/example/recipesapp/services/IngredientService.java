package com.example.recipesapp.services;
import com.example.recipesapp.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Integer createIngredient(Ingredient ingredient);

    Ingredient getIngredientId(Integer ingredientId);

    Ingredient editIngredient(Integer ingredientId, Ingredient ingredient);

    Ingredient deleteIngredient(Integer ingredientId);

    void deleteAllIngredient();

    Collection<Ingredient> getAllIngredients();
}

