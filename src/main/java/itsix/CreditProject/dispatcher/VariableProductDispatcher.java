package itsix.CreditProject.dispatcher;

import itsix.CreditProject.models.interfaces.IProduct;

public class VariableProductDispatcher implements IDispatcher {

	private static final long serialVersionUID = 1L;

	@Override
	public IProduct dispatch(IProduct product) {
		return product;
	}

}
