package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.FilesIngredientsService;
import com.example.recipesapp.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer ingredientId = 0;
    private final FilesIngredientsService filesIngredientsService;

    public IngredientServiceImpl(FilesIngredientsService filesIngredientsService) {
        this.filesIngredientsService = filesIngredientsService;
    }
    @PostConstruct
    private void init() {
        try {
            readToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Integer createIngredient(Ingredient ingredient) {
        ingredientMap.put(ingredientId, ingredient);
        saveToFile();
        return ingredientId++;
    }
    @Override
    public Ingredient editIngredient(Integer ingredientId, Ingredient ingredient) {
        if (ingredientMap.containsKey(ingredientId)) {
            ingredientMap.put(ingredientId, ingredient);
            saveToFile();
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
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesIngredientsService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void readToFile() {
        try {
            String json = filesIngredientsService.readFromFile();
            ingredientMap= new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
