package itsix.CreditProject.controllers.implementation;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.CreditView;
import itsix.CreditProject.views.NewCreditView;

public class AccountController implements IAccountController {

	private static final long serialVersionUID = 1L;

	private IRepository repository;

	private AccountView accountView;
	private CreditView creditView;
	private NewCreditView newCreditView;

	private IAccount account;
	private IClient client;

	private IOpertationBuilder operationBuilder;
	private IPaymentBuilder paymentBuilder;

	public AccountController(IRepository repository, IOpertationBuilder operationBuilder,
			IPaymentBuilder paymentBuilder, CreditView creditView) {
		this.repository = repository;
		this.operationBuilder = operationBuilder;
		this.paymentBuilder = paymentBuilder;
		this.creditView = creditView;
	}

	@Override
	public void setView(AccountView accountView) {
		this.accountView = accountView;
	}

	@Override
	public void updateFields() {
		accountView.setSold(account.getSold());
		accountView.setCurrency(account.getCurrencyName());
		accountView.setTableModel(account.getCredits());
	}

	@Override
	public void depositMoney() {
		account.deposit(accountView.getMoney());
		accountView.setResetMoneyText();
		client.addOperation(operationBuilder.buildDepositOperation(repository.getCurrentDay()));
	}

	@Override
	public IAccount getAccount() {
		return account;
	}

	@Override
	public void withdrawMoney() {
		try {
			account.withdraw(accountView.getMoney());
			accountView.setResetMoneyText();
			client.addOperation(operationBuilder.buildWithdrawOperation(repository.getCurrentDay()));
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void goToMakeCreditView() {
		newCreditView.show(account);
	}

	@Override
	public void toggleOperationButtons() {
		if (accountView.moneyValueisZero()) {
			accountView.disableButtons();
		} else {
			accountView.enableButtons();
		}
	}

	@Override
	public void goToCreditView() {

		ICredit credit = accountView.getSelectedCredit();

		credit.subscribe(accountView);

		IPayment payment = paymentBuilder.build(credit);

		creditView.show(payment, credit, account);

	}

	@Override
	public void setClient(IClient currentClient) {
		this.client = currentClient;
	}

	@Override
	public void setAccount(IAccount account) {
		this.account = account;
	}

	@Override
	public void setNewCreditView(NewCreditView newCreditView) {
		this.newCreditView = newCreditView;
	}

}
