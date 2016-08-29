package itsix.CreditProject.controllers;

import javax.swing.JOptionPane;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.views.AccountView;

public class AccountController implements IAccountController {

	private IClientRepository clientRepository;

	private AccountView view;

	private IAccount account;

	public AccountController(IClientRepository clientRepository, IAccount account) {
		this.clientRepository = clientRepository;
		this.account = account;
	}

	@Override
	public void setView(AccountView accountView) {
		this.view = accountView;
	}

	@Override
	public void updateFields() {
		view.setSold(account.getSold());
		view.setCurrency(account.getCurrencyName());

	}

	@Override
	public void depositMoney() {
		account.deposit(view.getMoney());
	}

	@Override
	public IAccount getAccount() {
		return account;
	}

	@Override
	public void withdrawMoney() {
		try {
			account.withdraw(view.getMoney());
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}
	}

}
