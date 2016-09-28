package itsix.CreditProject.validator;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IProduct;

public interface IProductValidator extends Serializable {

	IValidatorResult validateFields(IProduct credit);

}
