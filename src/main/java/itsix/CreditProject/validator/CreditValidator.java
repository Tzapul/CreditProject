package itsix.CreditProject.validator;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;

public class CreditValidator implements ICreditValidator {

	private IValidator validator;

	public CreditValidator(IValidator validator) {
		this.validator = validator;
	}

	@Override
	public IValidatorResult validateFields(ICredit credit, IProduct product) {
		validator.validateString("Name", credit.getName());
		validator.validateDouble("Money", credit.getBorrowedMoney());
		validator.validateInteger("Period", credit.getRemainingDays());
		validator.validateInInterval("Money", credit.getBorrowedMoney(), product.getMoneyInterval());
		validator.validateInInterval("Period", Double.valueOf(credit.getRemainingDays() / 30),
				product.getPeriodInterval());
		return validator.buildResult();
	}

}
