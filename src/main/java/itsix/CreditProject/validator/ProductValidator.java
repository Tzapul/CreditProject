package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.IProduct;

public class ProductValidator implements IProductValidator {

	private static final long serialVersionUID = 1L;
	
	private IValidator validator;
	
	public ProductValidator(IValidator validator) {
		this.validator = validator;
	}

	@Override
	public IValidatorResult validateFields(IProduct product) {
		
		validator.validateString("name", product.getName());
		validator.validateInteger("min",product.getMinValue());
		validator.validateInteger("max", product.getMaxValue());
		validator.validateDouble("interest rate", product.getInterestRate().doubleValue());
		validator.validateInterval("period", product.getPeriodInterval());
		validator.validateInterval("money", product.getMoneyInterval());
		
		return validator.buildResult();
	}

}
