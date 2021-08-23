package src.main.java.snacksale.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.snacksale.beans.Order;
import src.main.java.snacksale.beans.TransactionStatus;
import src.main.java.snacksale.controller.OrderController;
import src.main.java.snacksale.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderService {

	@Autowired
	OrderController orderController;

	@PostMapping("/insert")
	TransactionStatus InsertOrder(@RequestBody Order order) {
		return orderController.orderInsertController(order);

	}

	@GetMapping("/list")
	List<Order> ListOrder() {
		return orderController.orderListController();

	}

}
