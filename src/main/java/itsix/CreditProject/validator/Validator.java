package itsix.CreditProject.validator;

public class Validator implements IValidator {

	public StringBuilder errorMessageBuilder;

	public IValidatorResultBuilder resultBuilder;

	public Validator(StringBuilder errorMessageBuilder, IValidatorResultBuilder resultBuilder) {
		super();
		this.errorMessageBuilder = errorMessageBuilder;
		this.resultBuilder = resultBuilder;
	}

	public void validatePeriod(Integer period) {
		if (period <= 0 || period == null) {
			errorMessageBuilder.append("Invalid period input\n");
		}
	}

	public void validateInterestRate(Double interestRate) {
		if (interestRate <= 0 || interestRate == null) {
			errorMessageBuilder.append("Invalid interest rate input\n");
		}
	}

	public void validateMaxValue(Double maxValue) {
		if (maxValue <= 0 || maxValue == null) {
			errorMessageBuilder.append("Invalid max value input\n");
		}
	}

	public void validateMinValue(Double minValue) {
		if (minValue <= 0 || minValue == null) {
			errorMessageBuilder.append("Invalid min value input\n");
		}
	}

	@Override
	public void validateIntervalBounds(Double minValue, Double maxValue) {
		if (!(minValue < maxValue)) {
			errorMessageBuilder.append("Min should be lesser than Max\n");
		}
	}

	public void validateName(String name) {
		if (name.equals(null) || name.equals("")) {
			errorMessageBuilder.append("Invalid name input\n");
		}
	}

	@Override
	public IValidatorResult buildResult() {
		String errorMessage = errorMessageBuilder.toString();
		IValidatorResult result = resultBuilder.build(errorMessage.isEmpty(), errorMessage);

		errorMessageBuilder.setLength(0);
		return result;
	}

}
