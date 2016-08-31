package itsix.CreditProject.models.interfaces;

import java.util.List;

import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.IPublisher;

public interface IClient extends IPublisher {

	void addAccount(IAccount account);

	boolean hasSSN(Integer searchSSN);

	Integer getSSN();

	String getFirstname();

	String getLastname();

	String getAddress();

	List<IAccount> getAccounts();

	void update(String firstname, String lastname, String address);

	List<ICurrency> getCurrencies();

	void setPublisher(IInnerPublisher publisher);

	boolean hasTheSameSSN(IClient client);

	void addOperation(IOperation operation);

}
