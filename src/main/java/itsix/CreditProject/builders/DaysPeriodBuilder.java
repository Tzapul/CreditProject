package itsix.CreditProject.builders;


import itsix.CreditProject.models.IPeriod;
import itsix.CreditProject.models.Period;

public class DaysPeriodBuilder implements IPeriodBuilder {

	@Override
	public IPeriod build(Integer value) {
		return new Period(value * 30);
	}

}
