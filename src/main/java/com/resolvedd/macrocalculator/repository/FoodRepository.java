package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
