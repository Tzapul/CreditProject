package itsix.CreditProject.builders;

import itsix.CreditProject.models.ICredit;

public interface ICreditBuilder {

	ICredit build(String creditName, Double money, Double interestRate, Integer period);

}
