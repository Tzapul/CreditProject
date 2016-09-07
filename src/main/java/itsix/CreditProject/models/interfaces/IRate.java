package itsix.CreditProject.models.interfaces;

public interface IRate {

	Double getRate();

	void recalculate(Double previousValue, Double remainingMoney, Integer remainingDays);

	void recalculate(Double remainingMoney, Integer remainingDays);

}
