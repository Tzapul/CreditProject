package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IPayment;

public class CashPayment implements IPayment {

	private IPayment payment;
	
	public CashPayment(IPayment payment) {
		this.payment = payment;
	}

	@Override
	public void pay(Double money) {
		payment.pay(money);
	}
	
}
