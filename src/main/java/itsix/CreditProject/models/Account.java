package itsix.CreditProject.models;

import java.util.List;

public class Account implements IAccount {

	private String name;
	private ICurrency currency;
	private List<ICredit> credits;
	
	private ISold sold;
	
	
	public Account(String name, ICurrency currency, List<ICredit> credits, ISold sold) {
		super();
		this.name = name;
		this.currency = currency;
		this.credits = credits;
		this.sold = sold;
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

	@Override
	public Integer getSold() {
		return sold.getValue();
	}
	
	
}
