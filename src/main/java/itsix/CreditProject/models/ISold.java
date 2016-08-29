package itsix.CreditProject.models;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public interface ISold {

	Integer getValue();

	void add(Integer moneyToDeposit);

	void subtract(Integer money) throws SoldLesserThanZeroException;

}
