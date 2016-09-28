package itsix.CreditProject.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICurrencyRepository extends Serializable {

	Vector<ICurrency> getCurrencies();

	ICurrency getCreditIndex(IProduct credit);

	Vector<ICurrency> getRemainingCurrencies(List<ICurrency> currencies);

	boolean hasAllCurrenciesOf(IClient currentClient);

}
