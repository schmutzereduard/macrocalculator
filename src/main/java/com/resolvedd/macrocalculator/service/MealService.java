package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.repository.FoodRepository;
import com.resolvedd.macrocalculator.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final FoodRepository foodRepository;
    private final MealRepository mealRepository;

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Optional<Meal> findById(Long id) {
        return mealRepository.findById(id);
    }

    @Transactional
    public Meal save(Meal meal) {
        List<Food> managedFoods = meal.getFoods().stream()
                .map(food -> foodRepository.findById(food.getId())
                        .orElseThrow(() -> new RuntimeException("Food not found with id: " + food.getId())))
                .collect(Collectors.toList());

        meal.setFoods(managedFoods);
        return mealRepository.save(meal);
    }

    @Transactional
    public void delete(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new RuntimeException("Meal not found with id: " + id));
        meal.getFoods().clear();
        mealRepository.save(meal);
        mealRepository.delete(meal);
    }
}
