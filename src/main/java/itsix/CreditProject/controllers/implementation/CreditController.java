package itsix.CreditProject.controllers.implementation;

import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.views.CreditView;

public class CreditController implements ICreditController {

	private CreditView view;

	private ICredit credit;

	private IPayment cashPayment;
	private IPayment soldPayment;

	private IPayment currentPayment;

	public CreditController(ICredit credit, IPayment cashPayment, IPayment soldPayment) {
		super();
		this.credit = credit;
		this.cashPayment = cashPayment;
		this.soldPayment = soldPayment;
		this.currentPayment = cashPayment;
	}
	
	@Override
	public void populateFields() {
		view.setName(credit.getName());
		view.setPeriod(credit.getPeriod());
		view.setDailyRate(credit.getDailyRate());
		view.setRemainingMoney(credit.getRemainingMoney());
	}

	@Override
	public void payInAdvance() {
		currentPayment.pay(view.getAdvancedPaymentMoney());
		view.dispose();
	}

	@Override
	public void setView(CreditView view) {
		this.view = view;
	}

	@Override
	public void changeToCashPayment() {
		currentPayment = cashPayment;
	}

	@Override
	public void changeToSoldPayment() {
		currentPayment = soldPayment;
	}
}
