package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Recipe;
import com.resolvedd.macrocalculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> findAll(Long profileId) {
        return recipeRepository.findAllByProfileId(profileId);
    }

    public Optional<Recipe> findById(Long id, Long profileId) {
        return recipeRepository.findByIdAndProfileId(id, profileId);
    }

    @Transactional
    public Recipe save(Recipe recipe) {

        recipe.getRecipeFoods().forEach(recipeFood -> recipeFood.setRecipe(recipe));

        return recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteById(Long id, Long profileId) {
        recipeRepository.deleteByIdAndProfileId(id, profileId);
    }
}
