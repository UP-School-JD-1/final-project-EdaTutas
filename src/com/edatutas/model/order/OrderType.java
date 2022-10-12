package com.edatutas.model.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OrderType {

	/*
	 * Order class'ından almış olduğum değerlerle foodTypes ve drinkTypes adlı iki
	 * adet ArrayList oluşturdum. Oluşturmuş olduğum bu listelerden maksimum 2 adet
	 * yemek ve 1 adet içecek alabilecek şekilde random değerler almasını sağladım.
	 */
	public static final ArrayList<Order> foodTypes = new ArrayList<>(
			Arrays.asList(new Food("pizza", 500, 500), new Food("hamburger", 500, 600), new Food("fries", 350, 200)));

	public static final ArrayList<Order> drinkTypes = new ArrayList<>(
			Arrays.asList(new Drink("coffee", 500, 500), new Drink("coke", 500, 600), new Drink("sprite", 350, 200)));

	public static final ArrayList<Order> getRandomOrder(int foodCount, int drinkCount) {
		if (foodCount > 2 || drinkCount > 1)
			return null;
		ArrayList<Order> result = new ArrayList<>();
		int lastIndex = -1;
		for (int i = 0; i < foodCount; i++) {
			Random random = new Random();
			int randomIndex = random.nextInt(foodTypes.size());
			if (randomIndex != lastIndex)
				result.add(foodTypes.get(randomIndex));
			else
				i--;
		}
		for (int i = 0; i < drinkCount; i++) {
			Random random = new Random();
			int randomIndex = random.nextInt(drinkTypes.size());
			result.add(drinkTypes.get(randomIndex));
		}
		return result;
	}

}