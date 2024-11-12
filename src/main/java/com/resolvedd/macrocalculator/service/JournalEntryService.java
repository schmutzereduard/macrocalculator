package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.JournalEntry;
import com.resolvedd.macrocalculator.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public List<JournalEntry> findByJournalId(Long id){return journalEntryRepository.findByJournalId(id);}

    @Transactional
    public JournalEntry save(JournalEntry journalEntry) {

        journalEntry.getJournalFoods().forEach(journalFood -> journalFood.setJournalEntry(journalEntry));
        journalEntry.getJournalRecipes().forEach(journalRecipe -> journalRecipe.setJournalEntry(journalEntry));

        return journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public void deleteById(Long id) {
        journalEntryRepository.deleteById(id);
    }
}
