package itsix.CreditProject.models.implementations;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IOperation;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class Client implements IClient {

	private static final long serialVersionUID = 1L;

	private Integer sSN;
	private String firstname;
	private String lastname;
	private String address;

	private List<IAccount> accounts;
	private List<IOperation> operations;

	private transient IInnerPublisher publisher;

	public Client(Integer sSN, String firstname, String lastname, String address, IInnerPublisher publisher) {
		this.accounts = new ArrayList<>();
		this.operations = new ArrayList<>();
		this.sSN = sSN;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.publisher = publisher;
	}

	@Override
	public void addOperation(IOperation operation) {
		operations.add(operation);
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
		publisher.notifySubscribers();
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

	@Override
	public boolean hasTheSameSSN(IClient client) {
		return client.hasSSN(sSN);
	}

	@Override
	public void payCredits() {
		for (IAccount account : accounts) {
			account.payCredits();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (sSN == null) {
			if (other.sSN != null)
				return false;
		} else if (!sSN.equals(other.sSN))
			return false;
		return true;
	}

	@Override
	public IInnerPublisher getPublisher() {
		return publisher;
	}

}
