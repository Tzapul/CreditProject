package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IOperation;

public class WithdrawOperation implements IOperation {

	private static final long serialVersionUID = 1L;
	
	private IOperation operation;
	
	public WithdrawOperation(IOperation operation) {
		this.operation = operation;
	}

	@Override
	public Integer getDay() {
		return operation.getDay();
	}

}
