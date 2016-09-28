package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

public interface IRate extends Serializable {

	Double getRate();

	void recalculate(Double remainingMoney, Integer remainingDays);

	void recalculate(Double previousValue, Double remainingMoney, Integer remainingDays, Double interestRate);

}
