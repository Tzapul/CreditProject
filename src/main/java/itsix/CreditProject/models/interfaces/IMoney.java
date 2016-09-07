package itsix.CreditProject.models.interfaces;

import org.apache.commons.lang.mutable.MutableDouble;

public interface IMoney {

	Double getValue();

	void take(Double money);

	void recalculate(Double remainingMoney, MutableDouble interestRate, Integer remainingDays, Integer days);

}
