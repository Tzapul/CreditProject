package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IOperation;

public class WithdrawOperation implements IOperation {

	private IOperation operation;
	
	public WithdrawOperation(IOperation operation) {
		this.operation = operation;
	}

	@Override
	public Integer getDay() {
		return operation.getDay();
	}

}
