package src.main.java.snacksale.beans;

public class Ingredient {

	public String name;
	public Double value;
	public Integer amount;

	public Ingredient(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Ingredient() {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

}
