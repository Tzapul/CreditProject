package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.models.implementations.Money;
import itsix.CreditProject.models.interfaces.IMoney;

public class MoneyBuilder implements IMoneyBuilder {

	@Override
	public IMoney build(Double value) {
		return new Money(value);
	}

}
