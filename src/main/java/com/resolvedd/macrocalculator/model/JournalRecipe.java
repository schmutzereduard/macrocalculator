package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class JournalRecipe {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_entry_id")
    @JsonIgnore
    private JournalEntry journalEntry;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(nullable = false)
    private Double quantity;

    public JournalRecipe(JournalEntry journalEntry, Recipe recipe, Double quantity) {
        this.journalEntry = journalEntry;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public double getCalories() {
        return (this.quantity / this.recipe.getTotalWeight()) * this.recipe.getTotalCalories();
    }

    public double getCarbs() {
        return (this.quantity / this.recipe.getTotalWeight()) * this.recipe.getTotalCarbs();
    }
}
