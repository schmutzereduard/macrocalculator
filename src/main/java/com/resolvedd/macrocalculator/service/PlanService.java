package com.resolvedd.macrocalculator.service;

import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.repository.MealRepository;
import com.resolvedd.macrocalculator.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final MealRepository mealRepository;

    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    public Optional<Plan> findById(Long id) {
        return planRepository.findById(id);
    }

    @Transactional
    public Plan save(Plan plan) {
        List<Meal> managedMeals = plan.getMeals().stream()
                .map(meal -> mealRepository.findById(meal.getId())
                        .orElseThrow(() -> new RuntimeException("Meal not found with id: " + meal.getId())))
                .collect(Collectors.toList());

        plan.setMeals(managedMeals);
        return planRepository.save(plan);
    }

    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }
}
