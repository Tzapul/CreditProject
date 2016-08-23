package itsix.CreditProject.validator;

import itsix.CreditProject.models.ICredit;

public interface ICreditValidator {

	IValidatorResult validateFields(ICredit credit);

}
