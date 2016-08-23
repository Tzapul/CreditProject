package itsix.CreditProject.builders;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.models.Account;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;

public class AccountBuilder implements IAccountBuilder {

	private ICurrencyBuilder currencyBuillder;
	
	public AccountBuilder(ICurrencyBuilder currencyBuillder) {
		super();
		this.currencyBuillder = currencyBuillder;
	}


	@Override
	public IAccount buildDefaultAccount() {
		ICurrency currency = currencyBuillder.build("LEI", "RON");
		List<ICredit> credits = new ArrayList<>();
		return new Account("Default", currency, credits);
	}



}
