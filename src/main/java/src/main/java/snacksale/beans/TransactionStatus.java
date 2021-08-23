package src.main.java.snacksale.beans;

public class TransactionStatus {

	public boolean performedSuccessfully;
	public String description;

	public TransactionStatus(boolean performedSuccessfully, String description) {
		super();
		this.performedSuccessfully = performedSuccessfully;
		this.description = description;
	}



	public boolean isPerformedSuccessfully() {
		return performedSuccessfully;
	}

	public void setPerformedSuccessfully(boolean performedSuccessfully) {
		this.performedSuccessfully = performedSuccessfully;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
