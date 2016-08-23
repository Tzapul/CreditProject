package itsix.CreditProject.validator;

public interface IValidator {

	void validateName(String name);

	void validateMinValue(Double minValue);

	void validateMaxValue(Double maxValue);

	void validateInterestRate(Double interestRate);

	void validatePeriod(Integer period);

	IValidatorResult buildResult();

	void validateIntervalBounds(Double minValue, Double maxValue);

}
