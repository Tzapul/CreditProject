package itsix.CreditProject.builders;

import itsix.CreditProject.models.Product;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;

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
