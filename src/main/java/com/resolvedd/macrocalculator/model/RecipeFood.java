package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class RecipeFood {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private Double quantity;

    public RecipeFood(Recipe recipe, Food food, Double quantity) {
        this.recipe = recipe;
        this.food = food;
        this.quantity = quantity;
    }

    public double getCalories() {
        return (this.quantity / 100) * this.food.getCalories();
    }

    public double getCarbs() {
        return (this.quantity / 100) * this.food.getCarbs();
    }
}
