package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.RecipeFood;
import com.resolvedd.macrocalculator.repository.RecipeFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeFoodService {

    private final RecipeFoodRepository recipeFoodRepository;

    public List<RecipeFood> findAll() {
        return recipeFoodRepository.findAll();
    }

    public Optional<RecipeFood> findById(Long id) {
        return recipeFoodRepository.findById(id);
    }

    public List<RecipeFood> findByRecipeId(Long recipeId) {
        return recipeFoodRepository.findByRecipeId(recipeId);
    }

    public List<RecipeFood> findByFoodId(Long foodId) {
        return recipeFoodRepository.findByFoodId(foodId);
    }

    public RecipeFood save(RecipeFood recipeFood) {
        return recipeFoodRepository.save(recipeFood);
    }

    public void deleteById(Long id) {
        recipeFoodRepository.deleteById(id);
    }
}
