package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.model.JournalEntry;
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
    private final JournalEntryService journalEntryService;

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    public Optional<Journal> findById(Long id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public Journal save(Journal journal) {
        List<JournalEntry> journalEntries = journal.getEntries().stream()
                .map(journalEntryService::save) // Delegate saving of entries to JournalEntryService
                .collect(Collectors.toList());

        journal.setEntries(journalEntries);
        return journalRepository.save(journal);
    }


    @Transactional
    public void deleteById(Long id) {
        // Find all JournalEntries associated with the Journal by ID
        List<JournalEntry> journalEntries = journalEntryService.findByJournalId(id);

        // Delegate deletion of each entry to JournalEntryService
        for (JournalEntry journalEntry : journalEntries) {
            journalEntryService.deleteById(journalEntry.getId());
        }

        // Finally, delete the Journal itself
        journalRepository.deleteById(id);
    }


    public Optional<Journal> findByDay(LocalDate date) {
        return journalRepository.findByDate(date);
    }

    public List<Journal> findByMonth(LocalDate startDate, LocalDate endDate) {
        return journalRepository.findByDateBetween(startDate, endDate);
    }
}
