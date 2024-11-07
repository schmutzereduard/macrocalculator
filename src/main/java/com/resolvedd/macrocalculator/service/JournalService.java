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

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    public List<Journal> findByMonth(LocalDate startDate, LocalDate endDate) {
        return journalRepository.findByDateBetween(startDate, endDate);
    }

    public Optional<Journal> findById(Long id) {
        return journalRepository.findById(id);
    }

    public Optional<Journal> findByDay(LocalDate date) {
        return journalRepository.findByDate(date);
    }

    @Transactional
    public Journal save(Journal journal) {

        journal.getEntries().forEach(journalEntry -> journalEntry.setJournal(journal));

        return journalRepository.save(journal);
    }

    @Transactional
    public void deleteById(Long id) {
        journalRepository.deleteById(id);
    }
}
