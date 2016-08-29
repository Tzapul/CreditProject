package itsix.CreditProject.validator;

import itsix.CreditProject.models.IInterval;

public interface IValidator {

	void validateString(String fieldName, String value);

	void validateInteger(String fieldName, Integer value);

	void validateInterval(String fieldName, IInterval value);

	IValidatorResult buildResult();


	void validateDouble(String fieldName, Double value);


}
