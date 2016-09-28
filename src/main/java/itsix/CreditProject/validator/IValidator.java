package itsix.CreditProject.validator;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IInterval;

public interface IValidator extends Serializable {

	void validateString(String fieldName, String value);

	void validateInteger(String fieldName, Integer value);

	void validateInterval(String fieldName, IInterval value);

	IValidatorResult buildResult();

	void validateDouble(String fieldName, Double value);

	void validateInInterval(String fieldName, Double integer, IInterval interval);

}
