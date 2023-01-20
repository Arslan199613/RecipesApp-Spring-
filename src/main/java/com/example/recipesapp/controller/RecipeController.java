package com.example.recipesapp.controller;
import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity editRecipe(@PathVariable Integer recipeid, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(recipeid, recipe);
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
}