package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;

public interface ICreditBuilder extends Serializable {

	ICredit build(String creditName, Double money, MutableDouble interestRate, Integer period, IProduct product,
			IAccount account);

}
