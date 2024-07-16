package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    Optional<Journal> findByDate(LocalDate date);
    List<Journal> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
