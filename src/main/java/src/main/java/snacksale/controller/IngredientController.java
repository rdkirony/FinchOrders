package src.main.java.snacksale.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.main.java.snacksale.beans.Ingredient;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.repository.IngredientRepository;

@Service
public class IngredientController {

	@Autowired
	IngredientRepository repository;

	Logger logger = LoggerFactory.getLogger(IngredientController.class);

	public TransactionStatus insertIngredientController(List<Ingredient> ingredients) {
		try {
			if (canInsertIngredient(ingredients)) {
				for (Ingredient list : ingredients) {
					repository.insertIngredient(list);
				}
				return new TransactionStatus(true, "Ingredientes inseridos com sucesso");
			} else {
				return new TransactionStatus(false, "Não é possível inserir valores nulos");
			}

		} catch (Exception e) {
			logger.info(e.toString());
			return new TransactionStatus(false, e.getMessage());
		}

	}

	public List<Ingredient> listIngredientController() {
		try {
			return repository.getIngredientsList();
		} catch (Exception e) {
			throw e;
		}
	}

	private boolean canInsertIngredient(List<Ingredient> ingredients) {
		if (ingredients != null) {
			if (!ingredients.isEmpty()) {
				return true;
			}
			return false;
		}
		return false;

	}

}
