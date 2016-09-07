package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.IInterval;

public class Validator implements IValidator {

	public StringBuilder errorMessageBuilder;

	public IValidatorResultBuilder resultBuilder;

	public Validator(StringBuilder errorMessageBuilder, IValidatorResultBuilder resultBuilder) {
		super();
		this.errorMessageBuilder = errorMessageBuilder;
		this.resultBuilder = resultBuilder;
	}

	@Override
	public void validateString(String fieldName, String value) {
		if (value.equals(null) || value.equals("")) {
			errorMessageBuilder.append("Invalid " + fieldName + " input\n");
		}
	}

	@Override
	public void validateInteger(String fieldName, Integer value) {
		if (value <= 0 || value == null) {
			errorMessageBuilder.append("Invalid " + fieldName + " input\n");
		}
	}

	@Override
	public void validateInterval(String fieldName, IInterval value) {
		Integer min = value.getMin();
		Integer max = value.getMax();

		if (!(min < max)) {
			errorMessageBuilder.append("Min should be lesser than Max" + "(" + fieldName + ")" + "\n");
			return;
		}

		if (min <= 0 || min == null) {
			errorMessageBuilder.append("Invalid min value input(" + fieldName + ")" + "\n");
		}

		if (max <= 0 || max == null) {
			errorMessageBuilder.append("Invalid max value input(" + fieldName + ")" + "\n");
		}
	}
	
	@Override
	public void validateDouble(String fieldName, Double value) {
		if (value <= 0.0 || value == null) {
			errorMessageBuilder.append("Invalid " + fieldName + " input\n");
		}
	}

	@Override
	public IValidatorResult buildResult() {
		String errorMessage = errorMessageBuilder.toString();
		IValidatorResult result = resultBuilder.build(errorMessage.isEmpty(), errorMessage);

		errorMessageBuilder.setLength(0);
		return result;
	}

	@Override
	public void validateInInterval(String fieldName, Double integer, IInterval interval) {
		Integer min = interval.getMin();
		Integer max = interval.getMax();
		
		if(!(min <= integer && integer <= max)) {
			errorMessageBuilder.append(fieldName + " should be between " + min + " - " + max); 
		}
	}

}
