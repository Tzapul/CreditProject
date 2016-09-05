package itsix.CreditProject.builders.interfaces;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;

public interface IProductBuilder {

	IProduct build(String name, Integer minValue, Integer maxValue, MutableDouble interestRate, ICurrency currency,
			Integer minPeriod, Integer maxPeriod, IInnerPublisher publisher);
}
