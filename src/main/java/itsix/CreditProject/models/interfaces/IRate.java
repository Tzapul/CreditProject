package itsix.CreditProject.models.interfaces;

public interface IRate {

	Double getRate();

	void recalculate(Double value, Double value2, Integer numberOfDays);

}
