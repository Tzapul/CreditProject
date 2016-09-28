package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.models.implementations.Currency;
import itsix.CreditProject.models.interfaces.ICurrency;

public class CurrencyBuilder implements ICurrencyBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public ICurrency build(String name, String symbol) {
		return new Currency(name, symbol);
	}


}
