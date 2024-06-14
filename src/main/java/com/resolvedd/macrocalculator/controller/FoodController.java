package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.findAll();
    }

    @GetMapping("/{id}")
    public Food getFood(@PathVariable Long id) {
        return foodService.findById(id).orElse(null);
    }

    @PostMapping
    public Food saveFood(@RequestBody Food food) {
        return foodService.save(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public Food updateFood(@RequestBody Food food) {
        return foodService.save(food);
    }
}
