package itsix.CreditProject.repositories;

import java.util.Vector;

import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.models.ICurrency;

public interface ICurrencyRepository {

	Vector<ICurrency> getCurrencies();

	ICurrency getCreditIndex(IProduct credit);

}
