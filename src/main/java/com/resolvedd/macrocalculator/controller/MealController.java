package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.service.MealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/meals")
public class MealController {

   private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<Meal> getAllFoods() {
        return mealService.getAllMeals();
    }

    @PostMapping
    public Meal saveFood(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        mealService.deleteMeal(id);
    }

    @PutMapping
    public Meal updateFood(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }
}
