package itsix.CreditProject.controllers.implementation;

import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.views.CreditView;

public class CreditController implements ICreditController {

	private CreditView view;

	private IAccount account;

	private IPayment cashPayment;
	private IPayment soldPayment;
	
	private IPayment currentPayment;
	
	
	public CreditController(IAccount account, IPayment cashPayment, IPayment soldPayment, IPayment currentPayment) {
		super();
		this.account = account;
		this.cashPayment = cashPayment;
		this.soldPayment = soldPayment;
		this.currentPayment = currentPayment;
	}


	@Override
	public void payInAdvance() {
		currentPayment.pay(view.getAdvancedPaymentMoney());
	}
	
	@Override
	public void setView(CreditView view) {
		this.view = view;
	}
}
