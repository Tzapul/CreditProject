package itsix.CreditProject.builders;

import itsix.CreditProject.models.IMoney;
import itsix.CreditProject.models.Money;

public class MoneyBuilder implements IMoneyBuilder {

	@Override
	public IMoney build(Double value) {
		return new Money(value);
	}

}
