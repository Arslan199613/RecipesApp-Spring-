package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private Integer ingredientId = 1;
    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMap.put(ingredientId++, ingredient);
        return ingredient;
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
        ingredientMap = new TreeMap<>();
    }

    @Override
    public Ingredient getAllIngredient() {
        for (Ingredient ingredient : ingredientMap.values()) {
            return ingredient;
        }
        return null;
    }
}
