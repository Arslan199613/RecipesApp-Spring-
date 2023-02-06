package com.example.recipesapp.controller;

import com.example.recipesapp.services.FilesIngredientsService;
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
@RequestMapping("/filesWithIngredients")
@Tag(name="Files",description = "Crud-операции для работы с файлами")
public class FilesIngredientsController {
    private FilesIngredientsService filesIngredientsService;

    public FilesIngredientsController(FilesIngredientsService filesIngredientsService) {
        this.filesIngredientsService = filesIngredientsService;
    }
    @GetMapping("/export")
        @Operation(description = "Экспорт файла ингридиентов")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws IOException {
        File file = filesIngredientsService.getDataFile();
        InputStreamResource inputStreamResource = filesIngredientsService.exportFileIngredient();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(file.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                .body(inputStreamResource);
    }
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Импорт файла ингридиентов")
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) throws FileNotFoundException {
        filesIngredientsService.importFileIngredient(file);
        return ResponseEntity.ok().build();
    }
}

