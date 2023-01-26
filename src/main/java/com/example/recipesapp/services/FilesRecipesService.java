package com.example.recipesapp.services;

import java.io.File;

public interface FilesRecipesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
