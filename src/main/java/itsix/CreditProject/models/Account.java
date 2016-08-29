package itsix.CreditProject.models;

import java.util.List;

import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
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
	public void withdraw(Integer money) throws SoldLesserThanZeroException {
		sold.subtract(money);
		publisher.notifySubscribers();
	}

	@Override
	public ICurrency getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (credits == null) {
			if (other.credits != null)
				return false;
		} else if (!credits.equals(other.credits))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (sold == null) {
			if (other.sold != null)
				return false;
		} else if (!sold.equals(other.sold))
			return false;
		return true;
	}

	@Override
	public void addNew(ICredit credit) {
		credits.add(credit);
	}

	public List<ICredit> getCredits() {
		return credits;
	}

}
