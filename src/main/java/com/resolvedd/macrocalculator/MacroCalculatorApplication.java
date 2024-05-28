package com.resolvedd.macrocalculator;

import com.resolvedd.macrocalculator.model.Food;
import com.resolvedd.macrocalculator.model.FoodType;
import com.resolvedd.macrocalculator.service.FoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MacroCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacroCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(FoodService foodService) {
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

			foodService.saveFood(f1);
			foodService.saveFood(f2);
		};
	}
}
