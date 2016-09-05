package itsix.CreditProject.dispatcher;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.interfaces.IProduct;

public class FixedProductDispatcher implements IDispatcher {

	@Override
	public IProduct dispatch(IProduct product) {
		return new FixedInterestProduct(product.getName(), product.getMoneyInterval(), new MutableDouble(product.getInterestRate().doubleValue()),
				product.getCurrency(), product.getPeriodInterval(), null);
	}

}
