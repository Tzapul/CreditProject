package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IOperation;

public class DepositOperation implements IOperation {

	private static final long serialVersionUID = 1L;
	
	private IOperation operation;

	public DepositOperation(IOperation operation) {
		super();
		this.operation = operation;
	}

	@Override
	public Integer getDay() {
		return operation.getDay();
	}
	
}
