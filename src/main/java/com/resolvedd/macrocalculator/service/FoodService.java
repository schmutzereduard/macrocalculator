package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> findAll(Long profileId) {
        return foodRepository.findAllByProfileId(profileId);
    }

    public Optional<Food> findById(Long id, Long profileId) {
        return foodRepository.findByIdAndProfileId(id, profileId);
    }

    @Transactional
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    public void deleteById(Long id, Long profileId) {
        foodRepository.deleteByIdAndProfileId(id, profileId);
    }
}
