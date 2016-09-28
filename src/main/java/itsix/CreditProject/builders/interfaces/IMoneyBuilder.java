package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IMoney;

public interface IMoneyBuilder extends Serializable {

	IMoney build(Double money);

	
}
