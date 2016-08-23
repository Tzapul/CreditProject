package itsix.CreditProject.builders;

import itsix.CreditProject.models.Currency;
import itsix.CreditProject.models.ICurrency;

public class CurrencyBuilder implements ICurrencyBuilder {

	@Override
	public ICurrency build(String name, String symbol) {
		return new Currency(name, symbol);
	}


}
