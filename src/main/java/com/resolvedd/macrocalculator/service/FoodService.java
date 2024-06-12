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

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final MealRepository mealRepository;

    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    public void delete(Long foodId) {

        List<Meal> mealsWithFood = mealRepository.findAllByFoodsId(foodId);
        for (Meal meal : mealsWithFood) {
            meal.getFoods().removeIf(food -> food.getId().equals(foodId));
            mealRepository.save(meal);
        }

        foodRepository.deleteById(foodId);
    }
}
