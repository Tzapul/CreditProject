package itsix.CreditProject.builders.implementations;


import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.models.implementations.Period;
import itsix.CreditProject.models.interfaces.IPeriod;

public class DaysPeriodBuilder implements IPeriodBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IPeriod build(Integer value) {
		return new Period(value * 30);
	}

}
