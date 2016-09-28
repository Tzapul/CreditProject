package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.views.CreditView;

public interface ICreditController extends Serializable {

	void payInAdvance();

	void setView(CreditView view);

	void changeToCashPayment();

	void changeToSoldPayment();

	void populateFields();

	void updateToAllMoney();

	void show(IPayment payment, ICredit credit, IAccount account);

}
