package src.main.java.snacksale.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;

import src.main.java.snacksale.beans.Ingredient;
import src.main.java.snacksale.beans.Snack;

@Repository
public class SnackRepository extends GeneralRepositoryMethos {

	@Autowired
	IngredientRepository ingredientRepository;

	private ValueOperations<String, String> valueOperations;
	private RedisTemplate<String, String> redisTemplate;

	public SnackRepository(RedisTemplate<String, String> redisTemplate) {
		super(redisTemplate);
		this.valueOperations = redisTemplate.opsForValue();
		this.redisTemplate = redisTemplate;
	}

	public boolean insertSnack(Snack snack) {
		try {
			Gson gson = new Gson();
			String value = gson.toJson(snack);
			String key = "snack-" + formatKey(snack.getName());
			valueOperations.set(key, value);
			return true;

		} catch (Exception e) {

			throw e;
		}

	}

	public Double getTotalSnackValue(List<Ingredient> ingredients) {

		Double totalIngredientsValue = 0.0;
		for (Ingredient list : ingredients) {
			String ingredientKey = "ingredient-" + list.getName();
			Double value = ingredientRepository.getIngredientValueByKey(ingredientKey);
			totalIngredientsValue += value;
		}

		return totalIngredientsValue;

	}

	public List<Snack> getSnackList() {
		try {
			Set<String> snacksJson = redisTemplate.keys("snack-*");
			List<Snack> snackList = jsonToSnackList(snacksJson);
			return snackList;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Snack> jsonToSnackList(Set<String> snacksJson) {
		try {
			List<Snack> snackList = new ArrayList<Snack>();

			for (String list : snacksJson) {
				Snack snack = getSnackByKey(list);
				snackList.add(snack);
			}
			return snackList;
		} catch (Exception e) {
			throw e;
		}

	}

	public Snack getSnackByKey(String key) {
		try {
			Gson gson = new Gson();
			String snackJson = valueOperations.get(key);
			Snack snack = gson.fromJson(snackJson, Snack.class);
			return snack;
		} catch (Exception e) {
			throw e;
		}

	}

	public Double getSnackValueByKey(String key) {
		try {
			Snack snack = getSnackByKey(key);
			return snack.getValue();
		} catch (Exception e) {
			throw e;
		}

	}

}
