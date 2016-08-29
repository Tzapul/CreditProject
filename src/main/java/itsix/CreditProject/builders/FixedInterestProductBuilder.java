package itsix.CreditProject.builders;

import itsix.CreditProject.models.FixedInterestProduct;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.IProduct;

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
