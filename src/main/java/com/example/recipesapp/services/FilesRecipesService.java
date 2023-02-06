package com.example.recipesapp.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface FilesRecipesService {
    boolean saveToFile(String json);

    Path createTempFile(String suffix);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();

    void importFileRecipe(MultipartFile file) throws FileNotFoundException;

    InputStreamResource exportFileRecipe()throws FileNotFoundException;
}
