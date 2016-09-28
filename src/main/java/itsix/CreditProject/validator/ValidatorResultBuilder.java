package itsix.CreditProject.validator;

public class ValidatorResultBuilder implements IValidatorResultBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IValidatorResult build(boolean valid, String description) {
		return new ValidatorResult(valid, description);
	}

}
