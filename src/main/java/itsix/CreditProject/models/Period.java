package itsix.CreditProject.models;

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
