package itsix.CreditProject.controllers.interfaces;

import java.util.Vector;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.views.IEditProductView;

public interface IEditVariableProductController {

	void updateRealInterestRate();

	String getIndicator();

	void updateCredit();

	void populateFields();

	Vector<ICurrency> getCurrencies();

	void setView(IEditProductView editVariableView);

	void setProduct(IProduct product);

}
