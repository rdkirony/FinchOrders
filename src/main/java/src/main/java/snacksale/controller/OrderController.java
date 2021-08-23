package src.main.java.snacksale.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import src.main.java.snacksale.beans.Ingredient;
import src.main.java.snacksale.beans.Order;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.repository.IngredientRepository;
import src.main.java.snacksale.repository.OrderRepository;

@Controller
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	IngredientRepository ingredientRepository;
	Logger logger = LoggerFactory.getLogger(OrderController.class);

	public List<Order> orderListController() {
		try {
			return orderRepository.getOrderList();
		} catch (Exception e) {
			logger.info(e.toString());
			throw e;
		}
	}

	public TransactionStatus orderInsertController(Order order) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			String asGmt = df.format(new Date().getTime());
			order.setValue(calculeOrderValue(order));
			order.setDate(asGmt);
			order.setOrderId(orderRepository.generateId());
			orderRepository.insertOrder(order);
			return new TransactionStatus(true, "Pedido inserido com sucesso");

		} catch (Exception e) {
			logger.info(e.toString());
			throw e;
		}

	}

	private Double calculeOrderValue(Order order) {
		try {
			if (!order.isExtraIngredients()) {
				return orderRepository.getSnackValue(order.getSnack().getName());
			} else {
				Double snackValue = orderRepository.getSnackValue(order.getSnack().getName());
				Double discValue = discBurgerOrCheese(order.getSnack().getExtraIngredients());
				Double extraIngredientsValue = getAllIngredientsValue(order.getSnack().getExtraIngredients());
				if (isLight(order)) {
					return (snackValue + (extraIngredientsValue - discValue)) * 0.9;
				}
				return snackValue + (extraIngredientsValue - discValue);

			}
		} catch (Exception e) {
			throw e;
		}

	}

	private Double getAllIngredientsValue(List<Ingredient> ingredients) {
		try {
			Double value = 0.0;

			for (Ingredient list : ingredients) {
				String key = "ingredient-" + list.getName();
				value += ingredientRepository.getIngredientValueByKey(key) * list.getAmount();
			}
			return value;

		} catch (Exception e) {
			throw e;
		}

	}

	private boolean isLight(Order order) {
		if (order.getSnack().getName().equals("X-Bacon") || order.getSnack().getName().equals("X-EggBacon")) {
			return false;
		}
		for (Ingredient list : order.getSnack().getExtraIngredients()) {
			if (list.getName().equals("Alface")
					&& (order.getSnack().getName().equals("X-Burger") || order.getSnack().getName().equals("X-Egg"))) {
				return true;

			}
		}
		return false;
	}

	private Double discBurgerOrCheese(List<Ingredient> ingredients) {
		int burger = 0;
		int cheese = 0;
		Double burgerValue = ingredientRepository.getIngredientValueByKey("ingredient-Hamburguer");
		Double cheeseValue = ingredientRepository.getIngredientValueByKey("ingredient-Queijo");

		for (Ingredient ingredient : ingredients) {
			if (ingredient.getName().equals("Hamburguer")) {

				burger += ingredient.getAmount();
			}
			if (ingredient.getName().equals("Queijo")) {
				cheese += ingredient.getAmount();
			}
		}
		BigDecimal discBurger = new BigDecimal(0);
		BigDecimal discCheese = new BigDecimal(0);
		Double totalDiscBurger = 0.0;
		Double totalDiscCheese = 0.0;
		if (burger != 0) {
			discBurger = new BigDecimal(burger / 3).setScale(1, RoundingMode.DOWN);
			totalDiscBurger = discBurger.doubleValue() * burgerValue;
		}
		if (cheese != 0) {
			discCheese = new BigDecimal(cheese / 3).setScale(1, RoundingMode.DOWN);
			totalDiscCheese = discCheese.doubleValue() * cheeseValue;
		}

//

		return totalDiscBurger + totalDiscCheese;

	}

}
