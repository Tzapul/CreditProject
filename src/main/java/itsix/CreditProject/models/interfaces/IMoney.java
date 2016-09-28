package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

import org.apache.commons.lang.mutable.MutableDouble;

public interface IMoney extends Serializable {

	Double getValue();

	void take(Double money);

	void recalculate(Double borrowedMoney, Double previousInterestRate, MutableDouble interestRate);

}
