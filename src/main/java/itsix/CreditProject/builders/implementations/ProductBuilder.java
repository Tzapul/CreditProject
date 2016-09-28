package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.models.implementations.Product;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IProductBuilder;

public class ProductBuilder implements IProductBuilder {

	private static final long serialVersionUID = 1L;
	
	private IIntervalBuilder intervalBuilder;

	public ProductBuilder(IIntervalBuilder intervalBuilder) {
		super();
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public IProduct build(String name, Integer minValue, Integer maxValue, MutableDouble interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod, IInnerPublisher publisher) {
		
		IInterval moneyInterval = intervalBuilder.buildMoneyInterval(minValue, maxValue);
		IInterval periodInterval = intervalBuilder.buildPeriodInterval(minPeriod, maxPeriod);
		return new Product(name, moneyInterval, currency, interestRate, periodInterval, publisher);
	}

}
