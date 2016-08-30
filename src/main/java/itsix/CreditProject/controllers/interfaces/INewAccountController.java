package itsix.CreditProject.controllers.interfaces;

import java.util.Vector;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.views.NewAccountView;

public interface INewAccountController {

	void createNewAccount();

	void setView(NewAccountView view);

	Vector<ICurrency> getRemainingCurrencies();

}
