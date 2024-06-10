package com.resolvedd.macrocalculator;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.FoodType;
import com.resolvedd.macrocalculator.model.Meal;
import com.resolvedd.macrocalculator.service.FoodService;
import com.resolvedd.macrocalculator.service.MealService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MacroCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacroCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(FoodService foodService, MealService mealService) {
		return args -> {

			Food f1 = new Food();
			f1.setType(FoodType.FRUIT);
			f1.setName("Apple");
			f1.setCarbs(10);
			f1.setCalories(50);

			Food f2 = new Food();
			f2.setType(FoodType.MEAT);
			f2.setName("Chicken");
			f2.setCarbs(100);
			f2.setCalories(150);

			foodService.save(f1);
			foodService.save(f2);

			// Save individual foods first
			Food fm1 = new Food();
			fm1.setType(FoodType.VEGETABLE);
			fm1.setName("Beans");
			fm1.setCarbs(60);
			fm1.setCalories(200);
			foodService.save(fm1);

			Food fm2 = new Food();
			fm2.setType(FoodType.MEAT);
			fm2.setName("Sausages");
			fm2.setCarbs(80);
			fm2.setCalories(150);
			foodService.save(fm2);

			Food fm3 = new Food();
			fm3.setType(FoodType.VEGETABLE);
			fm3.setName("Tomato sauce");
			fm3.setCarbs(30);
			fm3.setCalories(100);
			foodService.save(fm3);

			Food fm4 = new Food();
			fm4.setType(FoodType.VEGETABLE);
			fm4.setName("Carrot");
			fm4.setCarbs(10);
			fm4.setCalories(20);
			foodService.save(fm4);

			Food fm5 = new Food();
			fm5.setType(FoodType.VEGETABLE);
			fm5.setName("Onion");
			fm5.setCarbs(15);
			fm5.setCalories(10);
			foodService.save(fm5);

			// Create a meal with persisted foods
			Meal m1 = new Meal();
			m1.setName("Beans w Sausages");
			m1.setFoods(Arrays.asList(fm1, fm2, fm3, fm4, fm5));
			mealService.save(m1);
		};
	}
}
