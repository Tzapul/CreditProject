package itsix.CreditProject.dispatcher;

import itsix.CreditProject.models.interfaces.IProduct;

public interface IDispatcher {

	IProduct dispatch(IProduct product);
}
