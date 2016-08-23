package itsix.CreditProject.validator;

public class ValidatorResultBuilder implements IValidatorResultBuilder {

	@Override
	public IValidatorResult build(boolean valid, String description) {
		return new ValidatorResult(valid, description);
	}

}
