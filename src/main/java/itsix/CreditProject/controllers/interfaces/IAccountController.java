package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.views.AccountView;

public interface IAccountController {

	void setView(AccountView accountView);

	void updateFields();

	void depositMoney();

	IAccount getAccount();

	void withdrawMoney();

	void goToMakeCreditView();

	void toggleOperationButtons();

	void showWindow(IAccount account);

}
