package com.example.recipesapp.services.impl;

import com.example.recipesapp.services.FilesIngredientsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesIngredientsServiceImpl implements FilesIngredientsService {

    @Value("${path.to.data.file2}")
    private String dataFile2Path;
    @Value("${name.of.data.file2}")
    private String dataFile2Name;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFile2Path,dataFile2Name), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFile2Path,dataFile2Name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFile2Path,dataFile2Name);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

