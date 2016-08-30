package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.ICredit;

public interface ICreditBuilder {

	ICredit build(String creditName, Double money, Double interestRate, Integer period);

}
