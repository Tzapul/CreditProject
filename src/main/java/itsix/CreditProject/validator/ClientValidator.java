package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.IClient;

public class ClientValidator implements IClientValidator {

	private IValidator validator;

	public ClientValidator(IValidator validator) {
		super();
		this.validator = validator;
	}

	@Override
	public IValidatorResult validateFields(IClient client) {
		validator.validateInteger("SSN", client.getSSN());
		validator.validateString("firstname", client.getFirstname());
		validator.validateString("lastname", client.getLastname());
		validator.validateString("address", client.getAddress());
	
		return validator.buildResult();
	}
}
