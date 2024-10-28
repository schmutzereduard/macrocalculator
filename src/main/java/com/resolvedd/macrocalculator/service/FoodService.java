package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.JournalFood;
import com.resolvedd.macrocalculator.model.RecipeFood;
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
    private final RecipeFoodService recipeFoodService;
    private final JournalFoodService journalFoodService;

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
    public void deleteById(Long id) {
        List<RecipeFood> recipeFoodsWithFood = recipeFoodService.findByFoodId(id);
        for (RecipeFood recipeFood : recipeFoodsWithFood) {
            recipeFoodService.deleteById(recipeFood.getId());
        }

        List<JournalFood> journalFoodsWithFood = journalFoodService.findByFoodId(id);
        for (JournalFood journalFood : journalFoodsWithFood) {
            journalFoodService.deleteById(journalFood.getId());
        }

        foodRepository.deleteById(id);
    }
}
