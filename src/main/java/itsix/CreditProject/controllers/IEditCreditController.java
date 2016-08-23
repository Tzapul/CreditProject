package itsix.CreditProject.controllers;

import java.util.Vector;

import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.views.EditCreditView;

public interface IEditCreditController {

	Vector<ICurrency> getCurrencies();

	void setView(EditCreditView view);

	void populateFields();

	void updateCredit();

}
