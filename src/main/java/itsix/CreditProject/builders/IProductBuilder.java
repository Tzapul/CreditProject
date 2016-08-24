package itsix.CreditProject.builders;

import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.models.ICurrency;

public interface IProductBuilder {

	IProduct build(String name, Integer minValue, Integer maxValue, Double interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod);
}
