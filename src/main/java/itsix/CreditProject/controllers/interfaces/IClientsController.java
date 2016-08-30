package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.models.IClient;
import itsix.CreditProject.views.ClientsView;

public interface IClientsController {

	void searchForClient();

	void setView(ClientsView clientView);

	void updateClient();

	void updateTableModel();

	IClient getCurrentClient();

	void hasAllAccounts();

	void showWindow();

}
