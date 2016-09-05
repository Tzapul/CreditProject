package itsix.CreditProject.dispatcher;

import itsix.CreditProject.models.interfaces.IProduct;

public class VariableProductDispatcher implements IDispatcher {

	@Override
	public IProduct dispatch(IProduct product) {
		return product;
	}

}
