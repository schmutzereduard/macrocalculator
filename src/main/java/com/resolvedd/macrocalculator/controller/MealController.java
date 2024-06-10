package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

   private final MealService mealService;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        return mealService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Meal saveFood(@RequestBody Meal meal) {
        return mealService.save(meal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public Meal updateMeal(@RequestBody Meal meal) {
        return mealService.save(meal);
    }
}
