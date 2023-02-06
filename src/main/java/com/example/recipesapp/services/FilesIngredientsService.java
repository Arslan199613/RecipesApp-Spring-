package com.example.recipesapp.services;

import com.example.recipesapp.exception.FileProcessingException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public interface FilesIngredientsService {
    boolean saveToFile(String json);

    String readFromFile() throws FileProcessingException;

    File getDataFile();

    boolean cleanDataFile();

    void importFileIngredient(MultipartFile file) throws FileNotFoundException;

    InputStreamResource exportFileIngredient()throws FileNotFoundException;
}
