package com.example.recipesapp.services.impl;
import com.example.recipesapp.exception.FileProcessingException;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.FilesRecipesService;
import com.example.recipesapp.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static Integer recipeId = 0;

    private final FilesRecipesService filesService;

    public RecipeServiceImpl(FilesRecipesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer createRecipes(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        saveToFile();
        return recipeId++;
    }

    @Override
    public Recipe editRecipe(Integer recipeId, Recipe recipe) {
        if (!recipeMap.containsKey(recipeId)) {
            throw new NotFoundException("Рецепт с заданным id не найден");
        }
        Recipe put = recipeMap.put(recipeId, recipe);
        saveToFile();
        return put;
        
    }

    @Override
    public Recipe getRecipesId(Integer recipeId) {
        if (!recipeMap.containsKey(recipeId)) {
            throw new NotFoundException("Рецепт с заданным id не найден");
        }
        return recipeMap.get(recipeId);
    }

    @Override
    public Recipe deleteRecipe(Integer recipeId) {
        if (!recipeMap.containsKey(recipeId)) {
            throw new FileProcessingException("Рецепт с заданным id не найден");
        }
        Recipe remove = recipeMap.remove(recipeId);
        saveToFile();
        return remove;
    }

    @Override
    public void deleteAllRecipe() {
        recipeMap.clear();
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }

    @Override
    public Path createReport() throws IOException {
        recipeMap.getOrDefault(recipeId, null);
        Path recipesText = filesService.createTempFile("Recipes_text");
        try (Writer writer = Files.newBufferedWriter(recipesText, StandardCharsets.UTF_8)) {
            for (Recipe recipes : recipeMap.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder instructions = new StringBuilder();
                for (Ingredient ingredient : recipes.getList()) {
                    ingredients.append(ingredient).append(", \n");
                }
                for (String instr : recipes.getSteps()) {
                    instructions.append("\n").append(instr);
                }
                writer.append(recipes.getName()).append("\n").append(" время приготовления ")
                        .append(String.valueOf(recipes.getTime())).append(" минут").append("\n").append(" Ингридиенты: \n")
                        .append(ingredients.toString()).append(" Инструкция: ")
                        .append(instructions.toString());
                writer.append("\n\r");
            }
        }
        return recipesText;
    }

    private void saveToFile() {
        try {
            DataFile dataFile = new DataFile(recipeId + 1,recipeMap);
            String json = new ObjectMapper().writeValueAsString(dataFile);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Файл не удалось найти");
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile();
            DataFile dataFile=new ObjectMapper().readValue(json, new TypeReference<DataFile>() {
            });
            recipeId = dataFile.getRecipeId();
            recipeMap = dataFile.getRecipeMap();
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Файл не удалось прочитать");
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class DataFile {
        private int recipeId;
        private Map<Integer, Recipe> recipeMap;
    }
}


