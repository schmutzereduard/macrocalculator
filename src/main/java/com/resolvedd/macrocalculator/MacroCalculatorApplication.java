package com.resolvedd.macrocalculator;

import com.resolvedd.macrocalculator.model.*;
import com.resolvedd.macrocalculator.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class MacroCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacroCalculatorApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FoodService foodService, RecipeService recipeService, RecipeFoodService recipeFoodService, PlanService planService) {
		return args -> {
			// Create a list of real foods
			List<Food> foods = Arrays.asList(
					new Food("Chicken Breast", FoodType.MEAT, 0, 165),
					new Food("Brown Rice", FoodType.GRAIN, 23, 110),
					new Food("Broccoli", FoodType.VEGETABLE, 6, 55),
					new Food("Salmon", FoodType.FISH, 0, 208),
					new Food("Quinoa", FoodType.GRAIN, 21, 120),
					new Food("Spinach", FoodType.VEGETABLE, 3, 23),
					new Food("Sweet Potato", FoodType.VEGETABLE, 20, 86),
					new Food("Apple", FoodType.FRUIT, 14, 52),
					new Food("Banana", FoodType.FRUIT, 27, 105),
					new Food("Eggs", FoodType.OTHER, 1, 78),
					new Food("Almonds", FoodType.NUTS, 22, 576),
					new Food("Greek Yogurt", FoodType.DAIRY, 4, 59),
					new Food("Blueberries", FoodType.FRUIT, 14, 57),
					new Food("Avocado", FoodType.FRUIT, 9, 160),
					new Food("Oats", FoodType.GRAIN, 27, 153),
					new Food("Kale", FoodType.VEGETABLE, 4, 33),
					new Food("Turkey Breast", FoodType.MEAT, 0, 135),
					new Food("Tuna", FoodType.FISH, 0, 144),
					new Food("Lentils", FoodType.GRAIN, 20, 116),
					new Food("Carrots", FoodType.VEGETABLE, 10, 41),
					new Food("Pineapple", FoodType.FRUIT, 13, 50),
					new Food("Beef Steak", FoodType.MEAT, 0, 271),
					new Food("Pasta", FoodType.GRAIN, 31, 131),
					new Food("Cheddar Cheese", FoodType.DAIRY, 1, 402),
					new Food("Black Beans", FoodType.GRAIN, 23, 132),
					new Food("Bell Peppers", FoodType.VEGETABLE, 6, 26),
					new Food("Cucumber", FoodType.VEGETABLE, 4, 16),
					new Food("Zucchini", FoodType.VEGETABLE, 3, 17),
					new Food("Strawberries", FoodType.FRUIT, 8, 32),
					new Food("Peanut Butter", FoodType.NUTS, 14, 588),
					new Food("Shrimp", FoodType.FISH, 1, 99),
					new Food("Oranges", FoodType.FRUIT, 12, 47),
					new Food("Mango", FoodType.FRUIT, 15, 60),
					new Food("Milk", FoodType.DAIRY, 5, 42),
					new Food("Walnuts", FoodType.NUTS, 14, 654),
					new Food("Green Beans", FoodType.VEGETABLE, 7, 31),
					new Food("Mushrooms", FoodType.VEGETABLE, 3, 22),
					new Food("Lamb", FoodType.MEAT, 0, 294),
					new Food("Bacon", FoodType.MEAT, 0, 541),
					new Food("Tofu", FoodType.OTHER, 2, 76),
					new Food("Lettuce", FoodType.VEGETABLE, 2, 15),
					new Food("Chia Seeds", FoodType.GRAIN, 42, 486),
					new Food("Cottage Cheese", FoodType.DAIRY, 3, 98),
					new Food("Grapes", FoodType.FRUIT, 17, 69),
					new Food("Pumpkin", FoodType.VEGETABLE, 7, 26),
					new Food("Cabbage", FoodType.VEGETABLE, 6, 25),
					new Food("Eggplant", FoodType.VEGETABLE, 6, 25)
			);

			// Save the foods to the database
			foods.forEach(foodService::save);

			// Create recipes using these foods
			List<Recipe> recipes = new ArrayList<>();
			for (int i = 1; i <= 25; i++) {
				Recipe recipe = new Recipe("Recipe " + i, new ArrayList<>());
				recipeService.save(recipe);

				// Randomly pick 3 foods for each recipe
				Collections.shuffle(foods);
				List<RecipeFood> recipeFoods = new ArrayList<>();
				for (int j = 0; j < 3; j++) {
					Food food = foods.get(j);
					RecipeFood recipeFood = new RecipeFood(recipe, food, (j + 1) * 100); // Quantity in grams
					recipeFoodService.save(recipeFood);
					recipeFoods.add(recipeFood);
				}

				recipe.setRecipeFoods(recipeFoods);
				recipeService.save(recipe);
				recipes.add(recipe);
			}

			// Create plans for a whole week using these recipes
			for (int day = 0; day < 7; day++) {
				LocalDate date = LocalDate.now().plusDays(day);
				Collections.shuffle(recipes);
				List<Recipe> dailyRecipes = recipes.subList(0, 3); // Select 3 recipes for each day
				Plan plan = new Plan(date, new ArrayList<>(dailyRecipes), "");
				planService.save(plan);
			}
		};
	}

}
