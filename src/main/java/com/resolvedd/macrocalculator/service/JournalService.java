package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.model.JournalRecipe;
import com.resolvedd.macrocalculator.repository.JournalRecipeRepository;
import com.resolvedd.macrocalculator.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final JournalRecipeRepository journalRecipeRepository;

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    public Optional<Journal> findById(Long id) {
        return journalRepository.findById(id);
    }

    public Journal save(Journal journal) {
        return journalRepository.save(journal);
    }

    @Transactional
    public void deleteById(Long id) {
        List<JournalRecipe> journalRecipes = journalRecipeRepository.findByJournalEntryId(id);
        for (JournalRecipe journalRecipe : journalRecipes) {
            journalRecipeRepository.delete(journalRecipe);
        }

        journalRepository.deleteById(id);
    }

    public Optional<Journal> findByDay(LocalDate date) {
        return journalRepository.findByDate(date);
    }

    public List<Journal> findByMonth(LocalDate startDate, LocalDate endDate) {
        return journalRepository.findByDateBetween(startDate, endDate);
    }
}
