package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.models.implementations.Interval;
import itsix.CreditProject.models.implementations.MoneyInterval;
import itsix.CreditProject.models.implementations.PeriodInterval;
import itsix.CreditProject.models.interfaces.IInterval;

public class IntervalBuilder implements IIntervalBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IInterval buildPeriodInterval(Integer min, Integer max) {
		IInterval interval = new Interval(min, max);
		return new PeriodInterval(interval);
	}
	
	@Override
	public IInterval buildMoneyInterval(Integer min, Integer max) {
		IInterval interval = new Interval(min, max);
		return new MoneyInterval(interval);
	}

}
