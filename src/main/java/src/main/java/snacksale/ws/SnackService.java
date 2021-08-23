package src.main.java.snacksale.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.snacksale.beans.Snack;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.controller.SnackController;

@RestController
@RequestMapping("/snack")
public class SnackService {

	@Autowired
	SnackController snackController;

	@PostMapping("/insert")
	public TransactionStatus insertSnack(@RequestBody List<Snack> snacks) {

		return snackController.snackInsertController(snacks);

	}

	@GetMapping("/list")
	public List<Snack> ListSnack() {

		return snackController.snackListController();

	}

}
