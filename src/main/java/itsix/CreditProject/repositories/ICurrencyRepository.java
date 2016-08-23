package itsix.CreditProject.repositories;

import java.util.Vector;

import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;

public interface ICurrencyRepository {

	Vector<ICurrency> getCurrencies();

	ICurrency getCreditIndex(ICredit credit);

}
