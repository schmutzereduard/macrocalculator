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
    public ResponseEntity<List<Food>> getFoods() {
        return ResponseEntity.ok(foodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable Long id) {
        return foodService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Food> saveFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @PutMapping
    public ResponseEntity<Food> updateFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
