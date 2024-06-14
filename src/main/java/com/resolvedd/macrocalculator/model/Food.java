package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double carbs;
    private double calories;
    private FoodType type;

    public Food(String name, double carbs, double calories, FoodType type) {
        this.name = name;
        this.carbs = carbs;
        this.calories = calories;
        this.type = type;
    }

    @ManyToMany(mappedBy = "foods")
    @JsonIgnore
    private List<Recipe> recipes;
}
