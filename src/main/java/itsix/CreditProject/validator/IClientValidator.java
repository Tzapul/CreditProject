package itsix.CreditProject.validator;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IClient;

public interface IClientValidator extends Serializable {

	IValidatorResult validateFields(IClient client);

}
