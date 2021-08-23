package src.main.java.snacksale.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import src.main.java.snacksale.beans.Order;
import src.main.java.snacksale.beans.Snack;

@Repository
public class OrderRepository extends GeneralRepositoryMethos {

	@Autowired
	SnackRepository snackRepository;

	private ValueOperations<String, String> valueOperations;
	private RedisTemplate<String,String> redisTemplate;

	public OrderRepository(RedisTemplate<String, String> redisTemplate) {
		super(redisTemplate);
		this.valueOperations = redisTemplate.opsForValue();
		this.redisTemplate = redisTemplate;
	}

	public boolean insertOrder(Order order) {
		try {
			order.setOrderId(generateId());
			Gson gson = new Gson();
			String value = gson.toJson(order);
			String key = "order-" + order.getDate() + "-" + order.getOrderId();
			valueOperations.set(key, value);
			return true;
		} catch (Exception e) {
			System.out.println("Deu algum erro: " + e);
			return false;
		}

	}

	public Double getSnackValue(String orderName) {
		try {
			String key = "snack-" + formatKey(orderName);
			Double value = snackRepository.getSnackValueByKey(key);
			return value;
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Order> getOrderList() {
		try {
			Set<String> orderJson = redisTemplate.keys("order-*");
			List<Order> orderList = jsonToSnackList(orderJson);
			return orderList;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Order> jsonToSnackList(Set<String> ordersJson) {
		try {
			List<Order> orderList = new ArrayList<Order>();

			for (String list : ordersJson) {
				Order order = getOrderByKey(list);
				orderList.add(order);
			}
			return orderList;
		} catch (Exception e) {
			throw e;
		}

	}

	public Order getOrderByKey(String key) {
		try {
			Gson gson = new Gson();
			String orderJson = valueOperations.get(key);
			Order order = gson.fromJson(orderJson, Order.class);
			return order;

		} catch (Exception e) {
			throw e;
		}
	}

}
