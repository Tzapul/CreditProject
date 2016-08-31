package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.IOperation;

public interface IOpertationBuilder {

	IOperation buildDepositOperation(Integer day);

	IOperation buildWithdrawOperation(Integer day);

}
