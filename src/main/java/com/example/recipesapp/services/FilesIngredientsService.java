package com.example.recipesapp.services;

public interface FilesIngredientsService {
    boolean saveToFile(String json);

    String readFromFile();
}
