package itsix.CreditProject.controllers.interfaces;

import java.util.Vector;

import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.views.NewProductView;

public interface INewProductController extends IWindowShower {

	void createNewCredit();

	Vector<ICurrency> getCurrencies();

	void setView(NewProductView newCreditView);

	void changeToFixedBuilder();

	void changeToVariableBuilder();

	void setLabelsVisible();

	void setLabelsInvisible();

	void updateRealInterestRate();

}
