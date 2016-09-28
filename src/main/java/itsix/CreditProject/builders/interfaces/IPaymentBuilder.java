package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;

public interface IPaymentBuilder extends Serializable {

	IPayment build(ICredit selectedCredit);

}
