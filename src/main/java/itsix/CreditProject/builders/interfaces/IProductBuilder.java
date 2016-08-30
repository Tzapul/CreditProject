package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;

public interface IProductBuilder {

	IProduct build(String name, Integer minValue, Integer maxValue, Double interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod);
}
