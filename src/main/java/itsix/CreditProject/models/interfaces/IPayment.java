package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;

public interface IPayment extends Serializable {

	void pay(Double money) throws SoldLesserThanZeroException;

}
