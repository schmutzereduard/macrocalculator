package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByFoodsId(Long id);
}
