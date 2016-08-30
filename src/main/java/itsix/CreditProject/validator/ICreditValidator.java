package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICreditValidator {

	IValidatorResult validateFields(ICredit credit, IProduct product);

}
