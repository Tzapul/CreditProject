package itsix.CreditProject.models;

import java.util.List;

public class Account implements IAccount {

	private String name;
	private ICurrency currency;
	private List<ICredit> products;
	
	
	public Account(String name, ICurrency currency, List<ICredit> products) {
		super();
		this.name = name;
		this.currency = currency;
		this.products = products;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getCurrencyName() {
		return currency.getName();
	}
	@Override
	public String getSymbol() {
		return currency.getSymbol();
	}
	
	
}
