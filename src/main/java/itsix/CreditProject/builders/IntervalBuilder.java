package itsix.CreditProject.builders;

import itsix.CreditProject.models.IInterval;
import itsix.CreditProject.models.Interval;

public class IntervalBuilder implements IIntervalBuilder {

	@Override
	public IInterval build(Double min, Double max) {
		return new Interval(min, max);
	}

}
