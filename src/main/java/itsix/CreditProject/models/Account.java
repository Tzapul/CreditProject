package itsix.CreditProject.models;

import java.util.List;

import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class Account implements IAccount {

	private ICurrency currency;
	private List<ICredit> credits;

	private ISold sold;
	
	private IInnerPublisher publisher;

	public Account(ICurrency currency, List<ICredit> credits, ISold sold, IInnerPublisher publisher) {
		super();
		this.currency = currency;
		this.credits = credits;
		this.sold = sold;
		this.publisher = publisher;
	}


	@Override
	public String getCurrencyName() {
		return currency.getName();
	}

	@Override
	public String getSymbol() {
		return currency.getSymbol();
	}

	@Override
	public Integer getSold() {
		return sold.getValue();
	}

	@Override
	public void deposit(Integer moeny) {
		sold.add(moeny);
		publisher.notifySubscribers();
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
	}

	@Override
	public void setPublisher(IInnerPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void withdraw(Integer money) {
		sold.subtract(money);
		publisher.notifySubscribers();
	}


	@Override
	public ICurrency getCurrency() {
		return currency;
	}

}
