package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICreditBuilder {

	ICredit build(String creditName, Double money, Double interestRate, Integer period, IProduct product);

}
