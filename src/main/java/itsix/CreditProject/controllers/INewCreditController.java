package itsix.CreditProject.controllers;

import java.util.Vector;

import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.views.NewProductView;

public interface INewCreditController {

	void createNewCredit();

	Vector<ICurrency> getCurrencies();

	void setView(NewProductView newCreditView);

	void changeToFixedBuilder();

	void changeToVariableBuilder();

	void setLabelsVisible();

	void setLabelsInvisible();

	void updateRealInterestRate();

}
