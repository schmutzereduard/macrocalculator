package com.resolvedd.macrocalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double carbs;
    private double kCalories;

    @ManyToMany(mappedBy = "foods")
    private List<Meal> meals;
}