package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.views.ClientView;

public interface IClientController {

	void goToNewClientView();

	void searchForClient();

	void setView(ClientView clientView);

	void updateClient();

	void goToAccountView();

	void goToNewAccountView();

	void updateTableModel();

	IClient getCurrentClient();

	void hasAllAccounts();

}
