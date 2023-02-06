package com.example.recipesapp.controller;

import com.example.recipesapp.services.FilesRecipesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/filesWithRecipes")
@Tag(name="Files",description = "Crud-операции для работы с файлами")
public class FilesRecipesController {

    private final FilesRecipesService filesRecipesService;

    public FilesRecipesController(FilesRecipesService filesRecipesService) {
        this.filesRecipesService = filesRecipesService;
    }

    @GetMapping("/export")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws IOException {
        File file = filesRecipesService.getDataFile();
        InputStreamResource inputStreamResource = filesRecipesService.exportFileRecipe();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(inputStreamResource);
    }
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла рецептов")
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) throws FileNotFoundException {
        filesRecipesService.importFileRecipe(file);
        return ResponseEntity.ok().build();
    }
}




