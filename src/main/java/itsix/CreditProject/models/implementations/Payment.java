package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;

public class Payment implements IPayment {

	private ICredit credit;

	public Payment(ICredit credit) {
		super();
		this.credit = credit;
	}

	@Override
	public void pay(Double money) {
		credit.recalculate(money);
	}

}
