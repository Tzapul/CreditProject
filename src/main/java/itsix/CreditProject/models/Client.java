package itsix.CreditProject.models;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class Client implements IClient {

	private Integer sSN;
	private String firstname;
	private String lastname;
	private String address;

	private List<IAccount> accounts;
	private IInnerPublisher publisher;

	public Client(Integer sSN, String firstname, String lastname, String address, IInnerPublisher publisher) {
		this.accounts = new ArrayList<>();
		this.sSN = sSN;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.publisher = publisher;
	}

	@Override
	public void addAccount(IAccount account) {
		accounts.add(account);
		publisher.notifySubscribers();
	}

	@Override
	public boolean hasSSN(Integer searchSSN) {
		return this.sSN.equals(searchSSN);
	}

	@Override
	public Integer getSSN() {
		return sSN;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public List<IAccount> getAccounts() {
		return accounts;
	}

	@Override
	public void update(String firstname, String lastname, String address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	@Override
	public List<ICurrency> getCurrencies() {
		List<ICurrency> currencies = new ArrayList<>();

		for (IAccount account : accounts) {
			currencies.add(account.getCurrency());
		}

		return currencies;
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

}
