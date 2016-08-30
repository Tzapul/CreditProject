package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.IClient;

public interface IClientValidator {

	IValidatorResult validateFields(IClient client);

}
