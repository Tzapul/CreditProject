package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;
import java.util.Vector;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.views.NewProductView;

public interface INewProductController extends Serializable {

	void createNewCredit();

	Vector<ICurrency> getCurrencies();

	void setView(NewProductView newCreditView);

	void changeToFixedBuilder();

	void changeToVariableBuilder();

	void setLabelsVisible();

	void setLabelsInvisible();

	void updateRealInterestRate();

}
