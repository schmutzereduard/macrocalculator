package com.resolvedd.macrocalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate date;
    private String notes;

    @ManyToMany
    @JoinTable(
            name = "plan_recipes",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();

    public Plan(LocalDate date, List<Recipe> recipes) {
        this.date = date;
        this.recipes = recipes;
    }
}
