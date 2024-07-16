package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.repository.JournalRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalRecipeService {

    private final JournalRecipeRepository journalRecipeRepository;

    public List<JournalRecipe> findAll() {
        return journalRecipeRepository.findAll();
    }

    public Optional<JournalRecipe> findById(Long id) {
        return journalRecipeRepository.findById(id);
    }

    public List<JournalRecipe> findByRecipeId(Long recipeId) {
        return journalRecipeRepository.findByRecipeId(recipeId);
    }

    public List<JournalRecipe> findByJournalEntryId(Long journalEntryId) {
        return journalRecipeRepository.findByJournalEntryId(journalEntryId);
    }

    public JournalRecipe save(JournalRecipe journalRecipe) {
        return journalRecipeRepository.save(journalRecipe);
    }

    public void deleteById(Long id) {
        journalRecipeRepository.deleteById(id);
    }
}
