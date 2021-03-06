package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;
import java.util.Vector;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.views.NewAccountView;

public interface INewAccountController extends Serializable {

	void createNewAccount();

	void setView(NewAccountView view);

	Vector<ICurrency> getRemainingCurrencies();

	void setClient(IClient currentClient);

}
