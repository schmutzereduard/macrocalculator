package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.repository.FoodRepository;
import com.resolvedd.macrocalculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public Recipe save(Recipe recipe) {
        List<Food> managedFoods = recipe.getFoods().stream()
                .map(food -> foodRepository.findById(food.getId())
                        .orElseThrow(() -> new RuntimeException("Food not found with id: " + food.getId())))
                .collect(Collectors.toList());

        recipe.setFoods(managedFoods);
        return recipeRepository.save(recipe);
    }

    public void delete(Long id) {
        Optional<Recipe> meal = recipeRepository.findById(id);
        meal.ifPresent(recipeRepository::delete);
    }
}
