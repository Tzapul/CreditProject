package itsix.CreditProject.validator;

import itsix.CreditProject.models.ICredit;

public class CreditValidator implements ICreditValidator {

	private IValidator validator;
	
	public CreditValidator(IValidator validator) {
		this.validator = validator;
	}

	@Override
	public IValidatorResult validateFields(ICredit credit) {
		
		validator.validateName(credit.getName());
		validator.validateMinValue(credit.getMinValue());
		validator.validateMaxValue(credit.getMaxValue());
		validator.validateInterestRate(credit.getInterestRate());
		validator.validatePeriod(credit.getPeriod());
		validator.validateIntervalBounds(credit.getMinValue(), credit.getMaxValue());
		
		return validator.buildResult();
	}

}
