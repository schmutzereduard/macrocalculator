package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.model.JournalEntry;
import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.repository.JournalEntryRepository;
import com.resolvedd.macrocalculator.repository.JournalFoodRepository;
import com.resolvedd.macrocalculator.repository.JournalRecipeRepository;
import com.resolvedd.macrocalculator.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalFoodRepository journalFoodRepository;
    private final JournalRecipeRepository journalRecipeRepository;

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    public Optional<Journal> findById(Long id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public Journal save(Journal journal) {
        // Fetch existing journal entries if updating an existing journal
        List<JournalEntry> journalEntries = journal.getEntries().stream().map(journalEntry -> {

            // Set the parent journal for each entry
            journalEntry.setJournal(journal);

            // Handle journal foods
            List<JournalFood> journalFoods = journalEntry.getJournalFoods().stream().map(journalFood -> {
                journalFood.setJournalEntry(journalEntry);
                return journalFoodRepository.save(journalFood); // Save and return each journal food
            }).collect(Collectors.toList());

            journalEntry.setJournalFoods(journalFoods); // Set saved foods back to the entry

            // Handle journal recipes
            List<JournalRecipe> journalRecipes = journalEntry.getJournalRecipes().stream().map(journalRecipe -> {
                journalRecipe.setJournalEntry(journalEntry);
                return journalRecipeRepository.save(journalRecipe); // Save and return each journal recipe
            }).collect(Collectors.toList());

            journalEntry.setJournalRecipes(journalRecipes); // Set saved recipes back to the entry

            return journalEntryRepository.save(journalEntry); // Save and return the journal entry
        }).collect(Collectors.toList());

        journal.setEntries(journalEntries); // Set saved entries back to the journal
        return journalRepository.save(journal); // Save and return the journal
    }


    @Transactional
    public void deleteById(Long id) {
        // Find all JournalEntries associated with the Journal by ID
        List<JournalEntry> journalEntries = journalEntryRepository.findByJournalId(id);

        for (JournalEntry journalEntry : journalEntries) {
            // Delete associated JournalFoods
            List<JournalFood> journalFoods = journalFoodRepository.findByJournalEntryId(journalEntry.getId());
            for (JournalFood journalFood : journalFoods) {
                journalFoodRepository.delete(journalFood);
            }

            // Delete associated JournalRecipes
            List<JournalRecipe> journalRecipes = journalRecipeRepository.findByJournalEntryId(journalEntry.getId());
            for (JournalRecipe journalRecipe : journalRecipes) {
                journalRecipeRepository.delete(journalRecipe);
            }

            // Finally, delete the JournalEntry
            journalEntryRepository.delete(journalEntry);
        }

        // Delete the Journal itself
        journalRepository.deleteById(id);
    }


    public Optional<Journal> findByDay(LocalDate date) {
        return journalRepository.findByDate(date);
    }

    public List<Journal> findByMonth(LocalDate startDate, LocalDate endDate) {
        return journalRepository.findByDateBetween(startDate, endDate);
    }
}
