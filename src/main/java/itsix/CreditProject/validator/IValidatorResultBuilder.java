package itsix.CreditProject.validator;

import java.io.Serializable;

public interface IValidatorResultBuilder extends Serializable {

	IValidatorResult build(boolean valid, String string);

}
