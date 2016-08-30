package itsix.CreditProject.repositories;

import java.util.List;
import java.util.Vector;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICurrencyRepository {

	Vector<ICurrency> getCurrencies();

	ICurrency getCreditIndex(IProduct credit);

	Vector<ICurrency> getRemainingCurrencies(List<ICurrency> currencies);

	boolean hasAllCurrenciesOf(IClient currentClient);

}
