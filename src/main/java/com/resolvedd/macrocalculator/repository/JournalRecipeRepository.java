package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.JournalRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRecipeRepository extends JpaRepository<JournalRecipe, Long> {

    List<JournalRecipe> findByRecipeId(Long recipeId);
    List<JournalRecipe> findByJournalEntryId(Long journalEntryId);
}
