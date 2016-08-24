package itsix.CreditProject.validator;

import itsix.CreditProject.models.IProduct;

public class ProductValidator implements IProductValidator {

	private IValidator validator;
	
	public ProductValidator(IValidator validator) {
		this.validator = validator;
	}

	@Override
	public IValidatorResult validateFields(IProduct credit) {
		
		validator.validateName(credit.getName());
		validator.validateMinValue(credit.getMinValue());
		validator.validateMaxValue(credit.getMaxValue());
		validator.validateInterestRate(credit.getInterestRate());
		validator.validatePeriod(credit.getPeriod());
		validator.validateIntervalBounds(credit.getMinValue(), credit.getMaxValue());
		
		return validator.buildResult();
	}

}
