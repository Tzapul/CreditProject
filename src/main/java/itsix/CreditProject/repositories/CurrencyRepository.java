package itsix.CreditProject.repositories;

import java.util.Vector;

import itsix.CreditProject.models.Currency;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;

public class CurrencyRepository implements ICurrencyRepository {

	private Vector<ICurrency> currencies;

	public CurrencyRepository() {
		currencies = new Vector<>();
		currencies.add(new Currency("LEI", "RON"));
		currencies.add(new Currency("EURO", "€"));
		currencies.add(new Currency("USD", "$"));
	}

	@Override
	public Vector<ICurrency> getCurrencies() {
		return currencies;
	}

	@Override
	public ICurrency getCreditIndex(ICredit credit) {
		for (ICurrency currency : currencies) {
			if(credit.hasCurrency(currency)) {
				return currency;
			}
		}
		return null;
	}
	
	
}
