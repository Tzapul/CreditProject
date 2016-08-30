package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.models.implementations.Rate;
import itsix.CreditProject.models.interfaces.IRate;

public class RateBuilder implements IRateBuilder {

	@Override
	public IRate build(Integer myPeriod, Double money, Double interestRate) {
		return new Rate((money * interestRate) / myPeriod);
	}

}
