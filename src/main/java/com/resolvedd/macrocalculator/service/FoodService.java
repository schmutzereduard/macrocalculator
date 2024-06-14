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

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    public void delete(Long foodId) {

        List<Recipe> mealsWithFood = recipeRepository.findAllByFoodsId(foodId);
        for (Recipe recipe : mealsWithFood) {
            recipe.getFoods().removeIf(food -> food.getId().equals(foodId));
            recipeRepository.save(recipe);
        }

        foodRepository.deleteById(foodId);
    }
}
