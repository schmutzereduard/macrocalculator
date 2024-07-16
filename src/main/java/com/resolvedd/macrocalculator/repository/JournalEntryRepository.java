package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
