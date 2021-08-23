package src.main.java.snacksale.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.snacksale.beans.Ingredient;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.controller.IngredientController;
import src.main.java.snacksale.repository.IngredientRepository;

@RestController
@RequestMapping("/ingredient")
public class IngredientService {

	@Autowired
	IngredientController ingredientController;

	@PostMapping("/insert")
	public TransactionStatus InsertIngredient(@RequestBody List<Ingredient> ingredients) {
		try {
			return ingredientController.insertIngredientController(ingredients);
		} catch (Exception e) {
			return new TransactionStatus(false, e.getMessage());
		}

	}

	@GetMapping("/list")
	public List<Ingredient> ListIngredient() {
		try {
			return ingredientController.listIngredientController();
		} catch (Exception e) {
			return null;
		}

	}
}
