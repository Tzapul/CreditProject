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

	private CreditView view;

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
		view.setCreditName(credit.getName());
		view.setPeriod(credit.getPeriod());
		view.setDailyRate(credit.getDailyRate());
		view.setRemainingMoney(credit.getRemainingMoney());
	}

	@Override
	public void payInAdvance() {
		try {
			currentPayment.pay(view.getAdvancedPaymentMoney());
			view.setVisible(false);
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}

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

	@Override
	public void updateToAllMoney() {
		view.setAdvancedPaymentMoney(credit.getRemainingMoney());
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

		view.setVisible(true);
	}
}
