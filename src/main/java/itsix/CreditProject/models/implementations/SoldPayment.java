package itsix.CreditProject.models.implementations;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.models.interfaces.ISoldPayment;

public class SoldPayment implements ISoldPayment {

	private static final long serialVersionUID = 1L;

	private IPayment payment;

	private IAccount account;

	public SoldPayment() {
		super();
	}

	@Override
	public void pay(Double money) throws SoldLesserThanZeroException {
		account.withdraw(money);
		payment.pay(money);

	}

	@Override
	public void setPayment(IPayment payment) {
		this.payment = payment;
	}

	@Override
	public void setAccount(IAccount account) {
		this.account = account;
	}

}
