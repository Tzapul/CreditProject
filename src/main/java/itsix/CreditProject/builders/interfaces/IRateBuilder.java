package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.IRate;

public interface IRateBuilder extends Serializable {

	IRate build(Integer myPeriod, Double money, MutableDouble interestRate);

}
