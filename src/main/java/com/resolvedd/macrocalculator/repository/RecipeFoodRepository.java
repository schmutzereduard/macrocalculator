package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.RecipeFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Long> {

    List<RecipeFood> findByFoodId(Long foodId);
    List<RecipeFood> findByRecipeId(Long recipeId);
}
