package itsix.CreditProject.validator;

public class ValidatorResult implements IValidatorResult {

	private boolean valid;

	private String description;

	public ValidatorResult(boolean valid, String description) {
		this.valid = valid;
		this.description = description;
	}

	@Override
	public boolean isNotValid() {
		return !valid;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
