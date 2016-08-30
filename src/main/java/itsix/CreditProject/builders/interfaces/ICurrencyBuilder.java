package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.ICurrency;

public interface ICurrencyBuilder {

	public ICurrency build(String name, String symbol);
}
