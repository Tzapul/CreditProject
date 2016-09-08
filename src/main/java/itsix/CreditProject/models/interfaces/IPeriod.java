package itsix.CreditProject.models.interfaces;

public interface IPeriod {

	Integer getNumberOfDays();

	void decrement();

	boolean isZero();

}
