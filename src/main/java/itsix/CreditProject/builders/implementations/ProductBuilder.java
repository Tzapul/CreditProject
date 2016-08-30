package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.models.implementations.Product;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IProductBuilder;

public class ProductBuilder implements IProductBuilder {

	private IIntervalBuilder intervalBuilder;

	public ProductBuilder(IIntervalBuilder intervalBuilder) {
		super();
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public IProduct build(String name, Integer minValue, Integer maxValue, Double interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod) {
		
		IInterval moneyInterval = intervalBuilder.buildMoneyInterval(minValue, maxValue);
		IInterval periodInterval = intervalBuilder.buildPeriodInterval(minPeriod, maxPeriod);
		return new Product(name, moneyInterval, currency, interestRate, periodInterval);
	}

}
