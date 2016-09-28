package itsix.CreditProject.dispatcher;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IProduct;

public interface IDispatcher extends Serializable {

	IProduct dispatch(IProduct product);
}
