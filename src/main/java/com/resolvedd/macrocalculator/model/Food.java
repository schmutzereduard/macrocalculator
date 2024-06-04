package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private double calories;
    private FoodType type;

    @ManyToMany(mappedBy = "foods")
    @JsonIgnore
    private List<Meal> meals;
}
