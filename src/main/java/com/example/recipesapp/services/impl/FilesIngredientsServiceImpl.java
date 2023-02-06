package com.example.recipesapp.services.impl;

import com.example.recipesapp.exception.FileProcessingException;
import com.example.recipesapp.services.FilesIngredientsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesIngredientsServiceImpl implements FilesIngredientsService {

    @Value("${path.to.files}")
    private String dataFile2Path;
    @Value("${name.of.ingredient.file}")
    private String dataFile2Name;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFile2Path, dataFile2Name), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        if (Files.exists(Path.of(dataFile2Path, dataFile2Name))) {
            try {
                return Files.readString(Path.of(dataFile2Path, dataFile2Name));
            } catch (IOException e) {
                throw new FileProcessingException("не удалось прочитайть файл");
            }
        } else {
            return "{ }";
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFile2Path + "/" + dataFile2Name);
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFile2Path, dataFile2Name);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void importFileIngredient(MultipartFile file) throws FileNotFoundException {
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
    public InputStreamResource exportFileIngredient() throws FileNotFoundException {
        File file = getDataFile();
        return new InputStreamResource(new FileInputStream(file));
    }
}
