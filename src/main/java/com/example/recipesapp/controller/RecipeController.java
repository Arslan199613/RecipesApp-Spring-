package com.example.recipesapp.controller;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("recipe")

public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipes = recipeService.createRecipes(recipe);
        return ResponseEntity.ok(createdRecipes);

    }
    @GetMapping("{recipeId}")
    public ResponseEntity getResipeId(@PathVariable Integer recipeId) {
        Recipe recipe = recipeService.getRecipesId(recipeId);
        if(recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}