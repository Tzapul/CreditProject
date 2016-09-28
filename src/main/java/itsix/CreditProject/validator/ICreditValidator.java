package itsix.CreditProject.validator;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICreditValidator extends Serializable {

	IValidatorResult validateFields(ICredit credit, IProduct product);

}
