package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public interface ISold extends Serializable {

	Double getValue();

	void add(Double money);

	void subtract(Double money) throws SoldLesserThanZeroException;

	void take(Double dailyRate);

}
