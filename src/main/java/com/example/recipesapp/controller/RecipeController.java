package com.example.recipesapp.controller;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты",description = "CRUD-операции и другие эндпоинты для работы с рецептами")

public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Integer> createRecipe(@RequestBody Recipe recipe) {
        Integer recipeId = recipeService.createRecipes(recipe);
        return ResponseEntity.ok(recipeId);
    }

    @GetMapping("/{recipeId}")
    @Operation(
            summary = "Поиск рецептов по id",
            description = "Вводим id "
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакции были найдены",
                    content= {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity getResipeId(@PathVariable Integer recipeId) {
        Recipe recipe = recipeService.getRecipesId(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer recipeId) {
        if (recipeService.deleteRecipe(recipeId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity editRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(recipeId, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllRecipe() {
        recipeService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<Recipe>> getAllResipe() {
        Collection<Recipe> allRecipe = recipeService.getAllRecipe();
        if (allRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allRecipe);
    }
    @GetMapping("/recipeReport")
    public ResponseEntity<InputStreamResource> getRecipesReport() throws FileNotFoundException {
        try {
            Path path = recipeService.createReport();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return  ResponseEntity.noContent().build();
        }
    }
}