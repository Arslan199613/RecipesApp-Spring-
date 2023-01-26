package com.example.recipesapp.services;

import java.io.File;

public interface FilesIngredientsService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
