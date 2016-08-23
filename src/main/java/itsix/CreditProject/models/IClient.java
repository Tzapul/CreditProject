package itsix.CreditProject.models;

import java.util.List;

public interface IClient {

	void addAccount(IAccount account);

	boolean hasSSN(Integer searchSSN);

	Integer getSSN();

	String getFirstname();

	String getLastname();

	String getAddress();

	List<IAccount> getAccounts();

	void update(String firstname, String lastname, String address);

}
