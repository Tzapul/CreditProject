package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;

public interface IPaymentBuilder {

	IPayment build(ICredit selectedCredit);

}
