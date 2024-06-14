package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.MealType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/meal-types")
public class MealTypeController {

    @GetMapping
    public List<MealType> getAllMealTypes() {
        return Arrays.asList(MealType.values());
    }
}
