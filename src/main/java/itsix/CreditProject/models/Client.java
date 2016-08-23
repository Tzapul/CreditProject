package itsix.CreditProject.models;

import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {

	private Integer sSN;
	private String firstname;
	private String lastname;
	private String address;

	private List<IAccount> accounts;
	
	public Client(Integer sSN, String firstname, String lastname, String address) {
		this.accounts = new ArrayList<>();
		this.sSN = sSN;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	@Override
	public void addAccount(IAccount account) {
		accounts.add(account);
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



}
