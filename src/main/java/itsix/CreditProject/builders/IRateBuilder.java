package itsix.CreditProject.builders;

import itsix.CreditProject.models.IRate;

public interface IRateBuilder {

	IRate build(Integer myPeriod, Double money);

}
