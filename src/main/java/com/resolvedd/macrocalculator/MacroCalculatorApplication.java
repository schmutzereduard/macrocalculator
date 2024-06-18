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
	CommandLineRunner runner(FoodService foodService, RecipeService recipeService,PlanService planService) {
		return args -> {
			// Create mock data for foods
			Food food1 = new Food("Chicken", FoodType.MEAT, 0, 200);
			Food food2 = new Food("Rice", FoodType.GRAIN, 45, 200);
			Food food3 = new Food("Broccoli", FoodType.VEGETABLE, 5, 50);

			foodService.save(food1);
			foodService.save(food2);
			foodService.save(food3);

			// Create mock data for recipes
			Recipe recipe1 = new Recipe("Chicken and Rice", Arrays.asList(food1, food2));
			Recipe recipe2 = new Recipe("Broccoli Salad", Collections.singletonList(food3));

			recipeService.save(recipe1);
			recipeService.save(recipe2);


			// Create mock data for plans
			Plan plan1 = new Plan(LocalDate.now(), Arrays.asList(recipe1, recipe2));

			planService.save(plan1);
		};
	}
}
