package com.resolvedd.macrocalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private double bloodSugarLevel;
    private double insulinUnits;

    @Enumerated(EnumType.STRING)
    private InsulinType insulinType;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    @JsonIgnore
    private Journal journal;

    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalRecipe> journalRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalFood> journalFoods = new ArrayList<>();

    public JournalEntry(LocalDateTime time, double bloodSugarBefore, double insulinCorrection, InsulinType insulinType) {
        this.time = time;
        this.bloodSugarLevel = bloodSugarBefore;
        this.insulinUnits = insulinCorrection;
        this.insulinType = insulinType;
    }

    public void addJournalRecipe(JournalRecipe journalRecipe) {
        this.journalRecipes.add(journalRecipe);
    }

    public void addJournalFood(JournalFood journalFood) {
        this.journalFoods.add(journalFood);
    }

    public double getTotalCalories() {
        double recipeCalories = journalRecipes.stream().mapToDouble(JournalRecipe::getCalories).sum();
        double foodCalories = journalFoods.stream().mapToDouble(JournalFood::getCalories).sum();
        return recipeCalories + foodCalories;
    }

    public double getTotalCarbs() {
        double recipeCarbs = journalRecipes.stream().mapToDouble(JournalRecipe::getCarbs).sum();
        double foodCarbs = journalFoods.stream().mapToDouble(JournalFood::getCarbs).sum();
        return recipeCarbs + foodCarbs;
    }
}
