package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.*;
import com.resolvedd.macrocalculator.repository.*;
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
    private final JournalRecipeRepository journalRecipeRepository;
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
                    recipeFood.setRecipe(recipe);
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

        List<JournalRecipe> journalRecipesWithRecipe = journalRecipeRepository.findByRecipeId(id);
        for (JournalRecipe journalRecipe : journalRecipesWithRecipe) {
            journalRecipeRepository.delete(journalRecipe);
        }

        recipeRepository.deleteById(id);
    }
}
