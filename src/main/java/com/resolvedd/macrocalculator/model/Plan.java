package com.resolvedd.macrocalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "breakfast_recipes",
            joinColumns = @JoinColumn(name = "breakfast_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> breakfast = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "lunch_recipes",
            joinColumns = @JoinColumn(name = "lunch_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> lunch = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "dinner_recipes",
            joinColumns = @JoinColumn(name = "dinner_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> dinner = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "snacks_recipes",
            joinColumns = @JoinColumn(name = "snack_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> snacks = new ArrayList<>();

    public Plan(LocalDate date, List<Recipe> breakfast, List<Recipe> lunch, List<Recipe> dinner, List<Recipe> snacks) {
        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
    }
}
