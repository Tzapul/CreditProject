package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.ICashPayment;

public class CashPayment implements ICashPayment {

	private IPayment payment;

	public CashPayment() {
		super();
	}

	@Override
	public void pay(Double money) {
		try {
			payment.pay(money);
		} catch (SoldLesserThanZeroException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPayment(IPayment payment) {
		this.payment = payment;
	}

}
