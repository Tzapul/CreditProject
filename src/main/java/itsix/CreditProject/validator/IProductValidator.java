package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.IProduct;

public interface IProductValidator {

	IValidatorResult validateFields(IProduct credit);

}
