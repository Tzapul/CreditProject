package itsix.CreditProject.builders;

import itsix.CreditProject.models.IRate;
import itsix.CreditProject.models.Rate;

public class RateBuilder implements IRateBuilder {

	@Override
	public IRate build(Integer myPeriod, Double money) {
		return new Rate((money * myPeriod) / 365);
	}

}
