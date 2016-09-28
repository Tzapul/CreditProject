package itsix.CreditProject.builders.implementations;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.interfaces.IAccountBuilder;
import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.models.implementations.Account;
import itsix.CreditProject.models.implementations.Sold;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.ISold;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;

public class AccountBuilder implements IAccountBuilder {

	private static final long serialVersionUID = 1L;
	
	private ICurrencyBuilder currencyBuillder;
	
	public AccountBuilder(ICurrencyBuilder currencyBuillder) {
		super();
		this.currencyBuillder = currencyBuillder;
	}


	@Override
	public IAccount buildDefaultAccount() {
		ICurrency currency = currencyBuillder.build("LEI", "RON");
		List<ICredit> credits = new ArrayList<>();
		ISold sold = new Sold(0.0);

		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		
		return new Account(currency, credits, sold, publisher);
	}



}
