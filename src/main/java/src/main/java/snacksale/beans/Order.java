package src.main.java.snacksale.beans;

public class Order {

	public Snack snack;
	public Double value;
	public String orderId;
	public String date;
	public boolean extraIngredients;

	public boolean isExtraIngredients() {
		return extraIngredients;
	}

	public void setExtraIngredients(boolean extraIngredients) {
		this.extraIngredients = extraIngredients;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Snack getSnack() {
		return snack;
	}

	public void setSnack(Snack snack) {
		this.snack = snack;
	}

}
