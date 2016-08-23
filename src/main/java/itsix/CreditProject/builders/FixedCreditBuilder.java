package itsix.CreditProject.builders;

import itsix.CreditProject.models.FixedInterestCredit;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.VariableInterestCredit;

public class FixedCreditBuilder implements IFixedCreditBuilder {

	private IIntervalBuilder intervalBuilder;
	
	public FixedCreditBuilder(IIntervalBuilder intervalBuilder) {
		this.intervalBuilder = intervalBuilder;
	}

	@Override
	public ICredit build(String name, Double minValue, Double maxValue, Double interestRate,
			ICurrency currency, Integer period) {
		
		IInterval interval = intervalBuilder.build(minValue, maxValue);
		return new FixedInterestCredit(name, interval, interestRate, currency, period);
	}



}
