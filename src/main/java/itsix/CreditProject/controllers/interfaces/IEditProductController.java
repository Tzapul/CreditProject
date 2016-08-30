package itsix.CreditProject.controllers.interfaces;

import java.util.Vector;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.views.EditProductView;

public interface IEditProductController {

	Vector<ICurrency> getCurrencies();

	void setView(EditProductView view);

	void populateFields();

	void updateCredit();

}
