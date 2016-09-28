package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IOperation;

public interface IOpertationBuilder extends Serializable {

	IOperation buildDepositOperation(Integer day);

	IOperation buildWithdrawOperation(Integer day);

}
