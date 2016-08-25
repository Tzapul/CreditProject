package itsix.CreditProject.controllers;

import itsix.CreditProject.models.IClient;
import itsix.CreditProject.views.ClientsView;

public interface IClientsController {

	void goToNewClientView();

	void searchForClient();

	void setView(ClientsView clientView);

	void updateClient();

	void goToAccountView();

	void goToNewAccountView();

	void updateTableModel();

	IClient getCurrentClient();

	void hasAllAccounts();

}
