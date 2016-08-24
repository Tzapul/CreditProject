package itsix.CreditProject.builders;

import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.Interval;
import itsix.CreditProject.models.MoneyInterval;
import itsix.CreditProject.models.PeriodInterval;

public class IntervalBuilder implements IIntervalBuilder {

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
