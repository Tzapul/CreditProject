package itsix.CreditProject.controllers;

import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.views.AccountView;

public class AccountController implements IAccountController {

	private IClientRepository clientRepository;

	private AccountView accountView;
	
	public AccountController(IClientRepository clientRepository) {
		this.clientRepository= clientRepository;
	}

	@Override
	public void setView(AccountView accountView) {
		this.accountView = accountView;
	}

}
