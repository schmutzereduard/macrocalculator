package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getRecipes() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return recipeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Recipe saveRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }

    @PutMapping
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
