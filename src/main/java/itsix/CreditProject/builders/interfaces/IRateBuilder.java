package itsix.CreditProject.builders.interfaces;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.IRate;

public interface IRateBuilder {

	IRate build(Integer myPeriod, Double money, MutableDouble interestRate);

}
