package itsix.CreditProject.builders;

import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.ICurrency;

public interface IFixedCreditBuilder extends ICreditBuilder {


	ICredit build(String name, Double minValue, Double maxValue, Double interestRate, ICurrency currency,
			Integer period);

}
