package com.example.recipesapp.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesRecipesService {
    boolean saveToFile(String json);

    Path createTempFile(String suffix);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
