package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.repository.MealRepository;
import com.resolvedd.macrocalculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final RecipeRepository recipeRepository;

    private final MealRepository mealRepository;

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Optional<Meal> findById(Long id) {
        return mealRepository.findById(id);
    }

    @Transactional
    public Meal save(Meal meal) {
        List<Recipe> managedRecipes = meal.getRecipes().stream()
                .map(recipe -> recipeRepository.findById(recipe.getId())
                        .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + recipe.getId())))
                .collect(Collectors.toList());

        meal.setRecipes(managedRecipes);
        return mealRepository.save(meal);
    }

    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }
}
