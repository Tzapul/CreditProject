package itsix.CreditProject.validator;

import itsix.CreditProject.models.IInterval;

public interface IValidator {

	void validateName(String name);

	void validateMinValue(Integer minValue);

	void validateMaxValue(Integer maxValue);

	void validateInterestRate(Double interestRate);

	void validatePeriod(IInterval iInterval);

	IValidatorResult buildResult();

	void validateIntervalBounds(Integer minValue, Integer maxValue);

}
