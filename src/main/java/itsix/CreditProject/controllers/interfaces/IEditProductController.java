package itsix.CreditProject.controllers.interfaces;

import java.util.Vector;

import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.views.EditProductView;

public interface IEditProductController extends IWindowShower {

	Vector<ICurrency> getCurrencies();

	void setView(EditProductView view);

	void populateFields();

	void updateCredit();

}
