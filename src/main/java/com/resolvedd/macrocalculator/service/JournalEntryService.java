package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.JournalEntry;
import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.repository.JournalEntryRepository;
import com.resolvedd.macrocalculator.repository.JournalFoodRepository;
import com.resolvedd.macrocalculator.repository.JournalRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalRecipeRepository journalRecipeRepository;
    private final JournalFoodRepository journalFoodRepository;

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry save(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public void deleteById(Long id) {
        List<JournalRecipe> journalRecipes = journalRecipeRepository.findByJournalEntryId(id);
        for (JournalRecipe journalRecipe : journalRecipes) {
            journalRecipeRepository.delete(journalRecipe);
        }

        List<JournalFood> journalFoods = journalFoodRepository.findByJournalEntryId(id);
        for (JournalFood journalFood : journalFoods) {
            journalFoodRepository.delete(journalFood);
        }

        journalEntryRepository.deleteById(id);
    }
}
