package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;

public interface IProductBuilder extends Serializable {

	IProduct build(String name, Integer minValue, Integer maxValue, MutableDouble interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod, IInnerPublisher publisher);
}
