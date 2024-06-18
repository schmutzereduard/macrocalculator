package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "recipe_foods",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods = new ArrayList<>();

    @ManyToMany(mappedBy = "recipes")
    @JsonIgnore
    private List<Plan> plans = new ArrayList<>();

    public Recipe(String name, List<Food> foods) {
        this.name = name;
        this.foods = foods;
    }
}
