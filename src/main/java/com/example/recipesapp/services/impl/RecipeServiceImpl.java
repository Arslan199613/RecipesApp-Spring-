package com.example.recipesapp.services.impl;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.FilesRecipesService;
import com.example.recipesapp.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static Integer recipeId = 0;

    private FilesRecipesService filesService;

    public RecipeServiceImpl(FilesRecipesService filesService) {
        this.filesService = filesService;
    }
    @PostConstruct
    private void init() {
        readToFile();
    }

    @Override
    public Integer createRecipes(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        saveToFile();
        return recipeId++;
    }

    @Override
    public Recipe editRecipe(Integer recipeId, Recipe recipe) {
        if (recipeMap.containsKey(recipeId)) {
            recipeMap.put(recipeId, recipe);
            saveToFile();
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
        var removed = recipeMap.remove(recipeId);
        return removed != null;
    }
    @Override
    public void deleteAllRecipe() {
        recipeMap.clear();
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void readToFile() {
        try {
            String json = filesService.readFromFile();
            recipeMap= new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}




