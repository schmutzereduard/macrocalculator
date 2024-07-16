package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.model.RecipeFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalFoodRepository extends JpaRepository<JournalFood, Long> {

    List<JournalFood> findByFoodId(Long foodId);
    List<JournalFood> findByJournalEntryId(Long journalEntryId);
}
