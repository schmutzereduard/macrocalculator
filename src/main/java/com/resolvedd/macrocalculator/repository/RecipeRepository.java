package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByProfileId(Long profileId);
    Optional<Recipe> findByIdAndProfileId(Long id, Long profileId);
    void deleteByIdAndProfileId(Long id, Long profileId);
}
