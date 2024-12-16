package com.resolvedd.macrocalculator.repository;

import com.resolvedd.macrocalculator.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByProfileId(Long profileId);
    Optional<Food> findByIdAndProfileId(Long id, Long profileId);
    void deleteByIdAndProfileId(Long id, Long profileId);
}
