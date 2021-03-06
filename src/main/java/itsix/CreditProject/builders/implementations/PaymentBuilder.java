package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.models.implementations.Payment;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;

public class PaymentBuilder implements IPaymentBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPayment build(ICredit selectedCredit) {
		return new Payment(selectedCredit);
	}

}
