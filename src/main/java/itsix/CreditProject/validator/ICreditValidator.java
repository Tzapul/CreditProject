package itsix.CreditProject.validator;

import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.IProduct;

public interface ICreditValidator {

	IValidatorResult validateFields(ICredit credit, IProduct product);

}
