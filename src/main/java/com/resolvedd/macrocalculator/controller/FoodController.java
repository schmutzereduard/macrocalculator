package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${gui.url}")
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @PostMapping
    public Food saveFood(@RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
    }

    @PutMapping
    public Food updateFood(@RequestBody Food food) {
        return foodService.saveFood(food);
    }
}
