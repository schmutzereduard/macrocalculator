package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class JournalRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_entry_id")
    @JsonIgnore
    private JournalEntry journalEntry;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private double quantity; // Quantity in grams

    public JournalRecipe(JournalEntry journalEntry, Recipe recipe, double quantity) {
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
