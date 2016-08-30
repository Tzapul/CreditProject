package itsix.CreditProject.models.implementations;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IPayment;

public class SoldPayment implements IPayment {

	private IPayment payment;
	
	private IAccount account;
	
	@Override
	public void pay(Double money) {
		try {
			account.withdraw(money);
			payment.pay(money);
		} catch (SoldLesserThanZeroException e) {
			
			e.printStackTrace();
		}
		
	}

}
