package com.example.recipesapp.controller;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingredient")

public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient){
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);

    }
    @GetMapping("{ingredientId}")
    public ResponseEntity getIngredientId(@PathVariable Integer ingredientId) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientId);
    }
}
