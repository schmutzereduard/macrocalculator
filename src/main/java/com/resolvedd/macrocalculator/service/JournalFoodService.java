package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.repository.JournalFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalFoodService {

    private final JournalFoodRepository journalFoodRepository;

    public List<JournalFood> findAll() {
        return journalFoodRepository.findAll();
    }

    public Optional<JournalFood> findById(Long id) {
        return journalFoodRepository.findById(id);
    }

    public List<JournalFood> findByJournalEntryId(Long journalEntryId) {
        return journalFoodRepository.findByJournalEntryId(journalEntryId);
    }

    public JournalFood save(JournalFood journalFood) {
        return journalFoodRepository.save(journalFood);
    }

    public void deleteById(Long id) {
        journalFoodRepository.deleteById(id);
    }
}
