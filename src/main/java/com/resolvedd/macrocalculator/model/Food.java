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
public class Food {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long profileId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType type;

    @Column(nullable = false)
    private Double carbs;

    @Column(nullable = false)
    private Double calories;

    private String comments;

    public Food(String name, FoodType type, double carbs, double calories) {
        this.name = name;
        this.carbs = carbs;
        this.calories = calories;
        this.type = type;
    }

    @OneToMany(mappedBy = "food", cascade = ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RecipeFood> recipeFoods = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = ALL, orphanRemoval = true)
    @JsonIgnore
    private List<JournalFood> journalFoods = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void setDefaultValues() {
        if (this.comments == null) {
            this.comments = "";
        }
    }
}
