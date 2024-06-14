package com.resolvedd.macrocalculator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private MealType type;

    @ManyToMany
    @JoinTable(
            name = "meal_recipes",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes;

    public Meal(MealType type, List<Recipe> recipes) {
        this.type = type;
        this.recipes = recipes;
    }
}
