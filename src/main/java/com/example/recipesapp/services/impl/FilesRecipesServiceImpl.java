package com.example.recipesapp.services.impl;

import com.example.recipesapp.exception.FileProcessingException;
import com.example.recipesapp.services.FilesRecipesService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesRecipesServiceImpl implements FilesRecipesService {
    @Value("${path.to.files}")
    private String dataFilePath;
    @Value("${name.of.recipe.file}")
    private String dataFileName;


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
            try {
                return Files.readString(Path.of(dataFilePath, dataFileName));
            } catch (IOException e) {
                throw new FileProcessingException("не удалось прочитайть файл");
            }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);

    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void importFileRecipe(MultipartFile file) throws FileNotFoundException {
        cleanDataFile();
        FileOutputStream fos = new FileOutputStream(getDataFile());
        try {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileProcessingException("Проблема сохранения файла");
        }
    }

    @Override
    public InputStreamResource exportFileRecipe() throws FileNotFoundException {
        File file = getDataFile();
        return new InputStreamResource(new FileInputStream(file));
    }
}
