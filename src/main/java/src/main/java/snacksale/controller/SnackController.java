package src.main.java.snacksale.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import src.main.java.snacksale.beans.Ingredient;
import src.main.java.snacksale.beans.Snack;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.repository.IngredientRepository;
import src.main.java.snacksale.repository.SnackRepository;

@Controller
public class SnackController {

	@Autowired
	SnackRepository snackRepository;

	Logger logger = LoggerFactory.getLogger(SnackController.class);

	public TransactionStatus snackInsertController(List<Snack> snacks) {
		try {
			if (canInsertSnack(snacks)) {
				for (Snack list : snacks) {
					if (list.getIngredients() != null && !list.getIngredients().isEmpty()) {
						list.setValue(snackRepository.getTotalSnackValue(list.getIngredients()));
					}
					snackRepository.insertSnack(list);
				}
				return new TransactionStatus(true, "Lanches inseridos com sucesso");
			} else {
				return new TransactionStatus(false, "Não é possível inserir valores nulos");
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return new TransactionStatus(false, e.toString());
		}

	}

	public boolean canInsertSnack(List<Snack> snacks) {
		if (snacks != null) {
			if (!snacks.isEmpty()) {
				return true;
			}
			return false;
		}
		return false;

	}

	public List<Snack> snackListController(){
		try {
			return snackRepository.getSnackList();
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}

}
