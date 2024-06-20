package com.resolvedd.macrocalculator;

import com.resolvedd.macrocalculator.model.*;
import com.resolvedd.macrocalculator.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class MacroCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacroCalculatorApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FoodService foodService, RecipeService recipeService,RecipeFoodService recipeFoodService, PlanService planService) {
		return args -> {
			// Create mock data for foods
			Food food1 = new Food("Chicken", FoodType.MEAT, 0, 165);
			Food food2 = new Food("Rice", FoodType.GRAIN, 28, 129);
			Food food3 = new Food("Broccoli", FoodType.VEGETABLE, 5, 50);

			foodService.save(food1);
			foodService.save(food2);
			foodService.save(food3);

			Recipe recipe1 = new Recipe("Chicken and Rice", Collections.emptyList());
			recipeService.save(recipe1);

			RecipeFood recipeFood1 = new RecipeFood(recipe1, food1, 200);
			RecipeFood recipeFood2 = new RecipeFood(recipe1, food2, 200);

			recipeFoodService.save(recipeFood1);
			recipeFoodService.save(recipeFood2);

			recipe1.setRecipeFoods(Arrays.asList(recipeFood1, recipeFood2));

			recipeService.save(recipe1);

			Plan pla1 = new Plan(LocalDate.now(), Arrays.asList(recipe1));
			planService.save(pla1);
		};
	}
}
