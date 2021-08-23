package src.main.java.snacksale.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import src.main.java.snacksale.beans.Ingredient;

@Repository
public class IngredientRepository extends GeneralRepositoryMethos {

	private ValueOperations<String, String> valueOperations;
	private RedisTemplate<String, String> redisTemplate;

	public IngredientRepository(RedisTemplate<String, String> redisTemplate) {
		super(redisTemplate);
		this.valueOperations = redisTemplate.opsForValue();
		this.redisTemplate = redisTemplate;
	}

	public void insertIngredient(Ingredient ingredient) {

		try {
			Gson gson = new Gson();
			String value = gson.toJson(ingredient);
			String key = "ingredient-" + ingredient.getName();
			valueOperations.set(key, value);

		} catch (Exception e) {
			throw e;
		}

	}

	public List<Ingredient> getIngredientsList() {
		try {
			Set<String> ingredientsJson = redisTemplate.keys("ingredient-*");
			List<Ingredient> ingredientsList = jsonToIngredientList(ingredientsJson);
			return ingredientsList;
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Ingredient> jsonToIngredientList(Set<String> ingredientsJson) {
		try {
			List<Ingredient> ingredientsList = new ArrayList<Ingredient>();

			for (String list : ingredientsJson) {
				Ingredient ingredient = getIngredientByKey(list);
				ingredientsList.add(ingredient);
			}
			return ingredientsList;
		} catch (Exception e) {
			throw e;
		}

	}

	public Ingredient getIngredientByKey(String key) {
		try {
			Gson gson = new Gson();
			String ingredientJson = valueOperations.get(key);
			Ingredient ingredient = gson.fromJson(ingredientJson, Ingredient.class);
			return ingredient;
		} catch (Exception e) {
			throw e;
		}

	}

	public Double getIngredientValueByKey(String key) {
		try {
			Ingredient ingredient = getIngredientByKey(key);
			return ingredient.getValue();
		} catch (Exception e) {
			throw e;
		}
	}

}
