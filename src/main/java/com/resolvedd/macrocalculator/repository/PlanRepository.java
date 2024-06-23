package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByDate(LocalDate date);
    List<Plan> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Plan> findAllByRecipesId(Long id);
}
