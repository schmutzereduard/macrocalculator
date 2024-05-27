package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
