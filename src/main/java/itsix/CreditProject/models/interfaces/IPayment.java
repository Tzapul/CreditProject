package itsix.CreditProject.models.interfaces;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public interface IPayment {

	void pay(Double money) throws SoldLesserThanZeroException;

}
