package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
