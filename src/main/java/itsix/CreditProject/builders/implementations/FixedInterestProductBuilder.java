package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IFixedInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;

public class FixedInterestProductBuilder implements IFixedInterestProductBuilder {

	private IIntervalBuilder intervalBuilder;

	public FixedInterestProductBuilder(IIntervalBuilder intervalBuilder) {
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public IProduct build(String name, Integer minValue, Integer maxValue, Double interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod) {

		IInterval moneyInterval = intervalBuilder.buildMoneyInterval(minValue, maxValue);
		IInterval periodInterval = intervalBuilder.buildPeriodInterval(minPeriod, maxPeriod);
		return new FixedInterestProduct(name, moneyInterval, interestRate, currency, periodInterval);
	}

}
