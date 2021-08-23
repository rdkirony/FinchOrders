package src.main.java.snacksale.beans;

import java.util.ArrayList;
import java.util.List;

public class Snack {

	public String name;
	public Double value;
	public List<Ingredient> ingredients = new ArrayList<Ingredient>();
	public List<Ingredient> extraIngredients = new ArrayList<Ingredient>();

	public Snack(String name, Double value, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.value = value;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Ingredient> getExtraIngredients() {
		return extraIngredients;
	}

	public void setExtraIngredients(List<Ingredient> extraIngredients) {
		this.extraIngredients = extraIngredients;
	}

}
