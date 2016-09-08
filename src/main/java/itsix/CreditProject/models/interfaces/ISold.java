package itsix.CreditProject.models.interfaces;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public interface ISold {

	Double getValue();

	void add(Double money);

	void subtract(Double money) throws SoldLesserThanZeroException;

	void take(Double dailyRate);

}
