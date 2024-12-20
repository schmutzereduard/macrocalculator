package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.FoodType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/food-types")
public class FoodTypeController {

    @GetMapping
    public ResponseEntity<List<FoodType>> getAllFoodTypes() {
        return ResponseEntity.ok(Arrays.asList(FoodType.values()));
    }
}
