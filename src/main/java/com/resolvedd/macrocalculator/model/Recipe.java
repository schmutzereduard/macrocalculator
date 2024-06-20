package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "recipe", cascade = ALL, orphanRemoval = true)
    private List<RecipeFood> recipeFoods = new ArrayList<>();

    @ManyToMany(mappedBy = "recipes")
    @JsonIgnore
    private List<Plan> plans = new ArrayList<>();

    public Recipe(String name, List<RecipeFood> recipeFoods) {
        this.name = name;
        this.recipeFoods = recipeFoods;
    }

    public double getTotalCalories() {
        return recipeFoods.stream().mapToDouble(RecipeFood::getCalories).sum();
    }

    public double getTotalCarbs() {
        return recipeFoods.stream().mapToDouble(RecipeFood::getCarbs).sum();
    }
}
