package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.ICurrency;

public interface ICurrencyBuilder extends Serializable{

	public ICurrency build(String name, String symbol);
}
