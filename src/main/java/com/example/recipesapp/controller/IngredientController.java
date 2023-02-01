package com.example.recipesapp.controller;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты",description = "CRUD-операции и другие эндпоинты для работы с ингридиентами")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Integer> createIngredient(@Valid @RequestBody Ingredient ingredient) {
        Integer ingredientId = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(ingredientId);

      }

    @GetMapping("/{ingredientId}")
    public ResponseEntity getIngredientId(@PathVariable Integer ingredientId) {
        Ingredient ingredient = ingredientService.getIngredientId(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer ingredientId) {
        if (ingredientService.deleteIngredient(ingredientId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{ingredientId}")
    public ResponseEntity editIngtedient(@PathVariable Integer ingredientId,@Valid @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(ingredientId, ingredient);
        if (ingredient1== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient1);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Ingredient>> getAllResipe() {
        Collection<Ingredient> allIngredients = ingredientService.getAllIngredients();
        if (allIngredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allIngredients);
    }
}