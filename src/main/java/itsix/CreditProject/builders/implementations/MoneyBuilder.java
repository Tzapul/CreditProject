package itsix.CreditProject.builders.implementations;

import java.text.DecimalFormat;

import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.models.implementations.Money;
import itsix.CreditProject.models.interfaces.IMoney;

public class MoneyBuilder implements IMoneyBuilder {

	@Override
	public IMoney build(Double value) {
		DecimalFormat df = new DecimalFormat("#.###");
		value = Double.valueOf(df.format(value));
		return new Money(value);
	}

}
