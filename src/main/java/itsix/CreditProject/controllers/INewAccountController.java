package itsix.CreditProject.controllers;

import java.util.Vector;

import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.views.NewAccountView;

public interface INewAccountController {

	void createNewAccount();

	void setView(NewAccountView view);

	Vector<ICurrency> getRemainingCurrencies();

}
