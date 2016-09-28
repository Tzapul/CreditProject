package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.models.implementations.DepositOperation;
import itsix.CreditProject.models.implementations.Operation;
import itsix.CreditProject.models.implementations.WithdrawOperation;
import itsix.CreditProject.models.interfaces.IOperation;

public class OperationBuilder implements IOpertationBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IOperation buildDepositOperation(Integer day) {
		IOperation operation = new Operation(day);
		return new DepositOperation(operation);
	}

	@Override
	public IOperation buildWithdrawOperation(Integer day) {
		IOperation operation = new Operation(day);
		return new WithdrawOperation(operation);
	}

	

}
