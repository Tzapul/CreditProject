package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import itsix.CreditProject.models.implementations.Currency;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;

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
	public ICurrency getCreditIndex(IProduct credit) {
		for (ICurrency currency : currencies) {
			if(credit.hasCurrency(currency)) {
				return currency;
			}
		}
		return null;
	}

	@Override
	public Vector<ICurrency> getRemainingCurrencies(List<ICurrency> currencies) {
		Vector<ICurrency> remainingCurrencies = new Vector<>(this.currencies);
		
		remainingCurrencies.removeAll(currencies);
		
		return remainingCurrencies;
	}

	@Override
	public boolean hasAllCurrenciesOf(IClient currentClient) {
		List<ICurrency> currencies = new ArrayList<>(this.currencies);
		
		currencies.removeAll(currentClient.getCurrencies());
		
		return currencies.isEmpty();
	}
	
	
}
