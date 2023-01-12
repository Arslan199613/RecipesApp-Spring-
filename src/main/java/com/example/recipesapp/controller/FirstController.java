package com.example.recipesapp.controller;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class FirstController {
    private RecipeService recipeService;

    public FirstController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String addAplication() {
        return "Запуск приложения!";
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
    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient){
        Ingredient createdIngredient = recipeService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);

    }
    @GetMapping("{ingredientId}")
    public ResponseEntity getIngredientId(@PathVariable Integer ingredientId) {
        Ingredient ingredient = recipeService.getIngredient(ingredientId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientId);
    }
}