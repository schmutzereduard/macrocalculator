package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.repository.FoodRepository;
import com.resolvedd.macrocalculator.repository.PlanRepository;
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
    private final PlanRepository planRepository;

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

    @Transactional
    public void deleteById(Long id) {
        List<Plan> planWithRecipes = planRepository.findAllByRecipesId(id);
        for (Plan plan : planWithRecipes) {
            plan.getRecipes().removeIf(food -> food.getId().equals(id));
            planRepository.save(plan);
        }

        recipeRepository.deleteById(id);
    }
}
