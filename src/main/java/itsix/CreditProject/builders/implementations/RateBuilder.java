package itsix.CreditProject.builders.implementations;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.models.implementations.Rate;
import itsix.CreditProject.models.interfaces.IRate;

public class RateBuilder implements IRateBuilder {

	private static final long serialVersionUID = 1L;

	@Override
	public IRate build(Integer myPeriod, Double money, MutableDouble interestRate) {
		return new Rate((money * ( 1 + interestRate.doubleValue() / 100) / myPeriod));
	}

}
