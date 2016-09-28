package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IPeriod;

public class Period implements IPeriod {

	private static final long serialVersionUID = 1L;
	
	private Integer days;

	public Period(Integer days) {
		this.days = days;
	}

	@Override
	public Integer getNumberOfDays() {
		return days;
	}

	@Override
	public void decrement() {
		days--;
	}

	@Override
	public boolean isZero() {
		return days == 0;
	}
	
}
