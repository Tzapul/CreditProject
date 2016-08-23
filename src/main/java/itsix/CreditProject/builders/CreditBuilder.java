package itsix.CreditProject.builders;

import itsix.CreditProject.models.Credit;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;

public class CreditBuilder implements ICreditBuilder {

	private IIntervalBuilder intervalBuilder;

	public CreditBuilder(IIntervalBuilder intervalBuilder) {
		super();
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public ICredit build(String name, Double minValue, Double maxValue, Double interestRate, ICurrency currency,
			Integer period) {
		
		IInterval interval = intervalBuilder.build(minValue, maxValue);
		return new Credit(name, interval, currency, interestRate, period);
	}

}
