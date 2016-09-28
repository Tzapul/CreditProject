package itsix.CreditProject.controllers.implementation;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICashPayment;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.models.interfaces.ISoldPayment;
import itsix.CreditProject.views.CreditView;

public class CreditController implements ICreditController {

	private static final long serialVersionUID = 1L;

	private CreditView creditView;

	private ICredit credit;

	private IPayment cashPayment;
	private IPayment soldPayment;

	private IPayment currentPayment;

	public CreditController(IPayment cashPayment, IPayment soldPayment) {
		super();
		this.cashPayment = cashPayment;
		this.soldPayment = soldPayment;
		this.currentPayment = cashPayment;
	}

	@Override
	public void populateFields() {
		creditView.setCreditName(credit.getName());
		creditView.setPeriod(credit.getPeriod());
		creditView.setDailyRate(credit.getDailyRate());
		creditView.setRemainingMoney(credit.getRemainingMoney());
	}

	@Override
	public void payInAdvance() {
		Double moneyToPay = creditView.getAdvancedPaymentMoney();
		if (creditView.getAdvancedPaymentMoney() > creditView.getRemainingMoney()) {
			moneyToPay = creditView.getRemainingMoney();
		}
		
		try {
			currentPayment.pay(moneyToPay);
			creditView.clear();
			creditView.setVisible(false);
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void setView(CreditView view) {
		this.creditView = view;
	}

	@Override
	public void changeToCashPayment() {
		currentPayment = cashPayment;
	}

	@Override
	public void changeToSoldPayment() {
		currentPayment = soldPayment;
	}

	@Override
	public void updateToAllMoney() {
		creditView.setAdvancedPaymentMoney(credit.getRemainingMoney());
	}

	public void setCredit(ICredit credit) {
		this.credit = credit;
	}

	@Override
	public void show(IPayment payment, ICredit credit, IAccount account) {

		((ISoldPayment) soldPayment).setPayment(payment);
		((ISoldPayment) soldPayment).setAccount(account);

		((ICashPayment) cashPayment).setPayment(payment);

		this.credit = credit;
		populateFields();

		creditView.setVisible(true);
	}
}
