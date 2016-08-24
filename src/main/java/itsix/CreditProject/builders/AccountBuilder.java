package itsix.CreditProject.builders;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.models.Account;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.ISold;
import itsix.CreditProject.models.Sold;

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
		ISold sold = new Sold(0);
		return new Account("Default", currency, credits, sold);
	}



}
