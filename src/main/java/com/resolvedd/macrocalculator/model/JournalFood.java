package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class JournalFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_entry_id")
    @JsonIgnore
    private JournalEntry journalEntry;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private double quantity; // Quantity in grams

    public JournalFood(JournalEntry journalEntry, Food food, double quantity) {
        this.journalEntry = journalEntry;
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
