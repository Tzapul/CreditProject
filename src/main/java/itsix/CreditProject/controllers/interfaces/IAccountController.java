package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.views.AccountView;

public interface IAccountController {

	void setView(AccountView accountView);

	void updateFields();

	void depositMoney();

	IAccount getAccount();

	void withdrawMoney();

	void goToMakeCreditView();

	void toggleOperationButtons();

	void goToCreditView();

	void setClient(IClient currentClient);

	void setAccount(IAccount account);

}
