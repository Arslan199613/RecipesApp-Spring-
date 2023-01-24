package com.example.recipesapp.services;

public interface FilesRecipesService {
    boolean saveToFile(String json);

    String readFromFile();
}
