package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.IPeriod;

public class Period implements IPeriod {

	private Integer days;
	
	public Period(Integer days) {
		this.days = days;
	}

	@Override
	public Integer getNumberOfDays() {
		return days;
	}

}
