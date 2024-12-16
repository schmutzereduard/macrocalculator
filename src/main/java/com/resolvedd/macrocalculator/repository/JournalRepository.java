package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findAllByProfileId(Long profileId);
    Optional<Journal> findByIdAndProfileId(Long id, Long profileId);
    Optional<Journal> findByDateAndProfileId(LocalDate date, Long profileId);
    List<Journal> findByDateBetweenAndProfileId(LocalDate startDate, LocalDate endDate, Long profileId);
    void deleteByIdAndProfileId(Long id, Long profileId);
}
