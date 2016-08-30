package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.IRate;

public interface IRateBuilder {

	IRate build(Integer myPeriod, Double money, Double interestRate);

}
