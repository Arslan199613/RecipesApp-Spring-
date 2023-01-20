package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer ingredientId = 0;
    @Override
    public Integer createIngredient(Ingredient ingredient) {
        ingredientMap.put(ingredientId, ingredient);
        return ingredientId++;
    }
    @Override
    public Ingredient editIngredient(Integer ingredientId, Ingredient ingredient) {
        if (ingredientMap.containsKey(ingredientId)) {
            ingredientMap.put(ingredientId, ingredient);
            return ingredient;
        }
        return null;
    }
    @Override
    public Ingredient getIngredientId(Integer ingredientId) {
        return ingredientMap.get(ingredientId);
    }

    @Override
    public boolean deleteIngredient(Integer ingredientId) {
        if (ingredientMap.containsKey(ingredientId)) {
            ingredientMap.remove(ingredientId);
            return true;
        }
        return false;
    }
    @Override
    public void deleteAllIngredient() {
        ingredientMap.clear();
    }

    @Override
    public Collection <Ingredient> getAllIngredients() {
        return ingredientMap.values();
    }
}
