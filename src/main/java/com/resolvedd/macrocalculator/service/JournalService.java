package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    public List<Journal> findAll(Long profileId) {
        return journalRepository.findAllByProfileId(profileId);
    }

    public List<Journal> findByMonth(LocalDate startDate, LocalDate endDate, Long profileId) {
        return journalRepository.findByDateBetweenAndProfileId(startDate, endDate, profileId);
    }

    public Optional<Journal> findById(Long id, Long profileId) {
        return journalRepository.findByIdAndProfileId(id, profileId);
    }

    public Optional<Journal> findByDay(LocalDate date, Long profileId) {
        return journalRepository.findByDateAndProfileId(date, profileId);
    }

    @Transactional
    public Journal save(Journal journal) {

        journal.getEntries().forEach(journalEntry -> {
            journalEntry.getJournalFoods().forEach(journalFood -> journalFood.setJournalEntry(journalEntry));
            journalEntry.getJournalRecipes().forEach(journalRecipe -> journalRecipe.setJournalEntry(journalEntry));
            journalEntry.setJournal(journal);
        });

        return journalRepository.save(journal);
    }

    @Transactional
    public void deleteById(Long id, Long profileId) {
        journalRepository.deleteByIdAndProfileId(id, profileId);
    }
}
