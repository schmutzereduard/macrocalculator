package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.JournalEntry;
import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalFoodService journalFoodService;
    private final JournalRecipeService journalRecipeService;

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public List<JournalEntry> findByJournalId(Long id){return journalEntryRepository.findByJournalId(id);}

    @Transactional
    public JournalEntry save(JournalEntry journalEntry) {
        // Handle journal foods
        List<JournalFood> journalFoods = journalEntry.getJournalFoods().stream().map(journalFood -> {
            journalFood.setJournalEntry(journalEntry);
            return journalFoodService.save(journalFood); // Save and return each journal food
        }).collect(Collectors.toList());

        journalEntry.setJournalFoods(journalFoods); // Set saved foods back to the entry

        // Handle journal recipes
        List<JournalRecipe> journalRecipes = journalEntry.getJournalRecipes().stream().map(journalRecipe -> {
            journalRecipe.setJournalEntry(journalEntry);
            return journalRecipeService.save(journalRecipe); // Save and return each journal recipe
        }).collect(Collectors.toList());

        journalEntry.setJournalRecipes(journalRecipes); // Set saved recipes back to the entry

        // Finally, save the JournalEntry and return
        return journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public void deleteById(Long id) {
        // Delete associated JournalFoods
        List<JournalFood> journalFoods = journalFoodService.findByJournalEntryId(id);
        for (JournalFood journalFood : journalFoods) {
            journalFoodService.deleteById(journalFood.getId());
        }

        // Delete associated JournalRecipes
        List<JournalRecipe> journalRecipes = journalRecipeService.findByJournalEntryId(id);
        for (JournalRecipe journalRecipe : journalRecipes) {
            journalRecipeService.deleteById(journalRecipe.getId());
        }

        // Finally, delete the JournalEntry itself
        journalEntryRepository.deleteById(id);
    }
}
