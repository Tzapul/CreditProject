package itsix.CreditProject.builders;

import itsix.CreditProject.models.ICurrency;

public interface ICurrencyBuilder {

	public ICurrency build(String name, String symbol);
}
