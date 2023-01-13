package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private Integer ingredientId = 1;
    @Override
    public Ingredient createIngredient(@NonNull Ingredient ingredient) {
        ingredientMap.put(ingredientId++, ingredient);
        return ingredient;
    }
    @Override
    public Ingredient getIngredient(@NonNull Integer ingredientId) {
        return ingredientMap.get(ingredientId);
    }
}
