package itsix.CreditProject.builders.implementations;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.builders.interfaces.IFixedInterestProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;

public class FixedInterestProductBuilder implements IFixedInterestProductBuilder {

	private static final long serialVersionUID = 1L;
	
	private IIntervalBuilder intervalBuilder;

	public FixedInterestProductBuilder(IIntervalBuilder intervalBuilder) {
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public IProduct build(String name, Integer minValue, Integer maxValue, MutableDouble interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod, IInnerPublisher publisher) {

		IInterval moneyInterval = intervalBuilder.buildMoneyInterval(minValue, maxValue);
		IInterval periodInterval = intervalBuilder.buildPeriodInterval(minPeriod, maxPeriod);
		return new FixedInterestProduct(name, moneyInterval, interestRate, currency, periodInterval, publisher);
	}

}
