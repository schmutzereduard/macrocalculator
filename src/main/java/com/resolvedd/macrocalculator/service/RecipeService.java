package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.model.RecipeFood;
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
    private final RecipeFoodService recipeFoodService;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public Recipe save(Recipe recipe) {
        List<RecipeFood> managedRecipeFoods = recipe.getRecipeFoods().stream()
                .map(recipeFood -> {
                    Food food = foodRepository.findById(recipeFood.getFood().getId())
                            .orElseThrow(() -> new RuntimeException("Food not found with id: " + recipeFood.getFood().getId()));
                    recipeFood.setFood(food);
                    return recipeFoodService.save(recipeFood);
                })
                .collect(Collectors.toList());

        recipe.setRecipeFoods(managedRecipeFoods);
        return recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteById(Long id) {
        List<RecipeFood> recipeFoodsWithRecipe = recipeFoodService.findByRecipeId(id);
        for (RecipeFood recipeFood : recipeFoodsWithRecipe) {
            recipeFoodService.deleteById(recipeFood.getId());
        }

        List<Plan> plansWithRecipe = planRepository.findAllByRecipesId(id);
        for (Plan plan : plansWithRecipe) {
            plan.getRecipes().removeIf(recipe -> recipe.getId().equals(id));
            planRepository.save(plan);
        }

        recipeRepository.deleteById(id);
    }
}
