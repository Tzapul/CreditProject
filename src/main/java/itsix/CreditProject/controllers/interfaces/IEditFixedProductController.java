package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;
import java.util.Vector;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.views.IEditProductView;

public interface IEditFixedProductController extends Serializable {

	Vector<ICurrency> getCurrencies();

	void populateFields();

	void updateCredit();

	void setView(IEditProductView editFixedView);

	void setProduct(IProduct product);

}
