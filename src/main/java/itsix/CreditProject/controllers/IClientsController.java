package itsix.CreditProject.controllers;

import itsix.CreditProject.views.ClientsView;

public interface IClientsController {

	void goToNewClientView();

	void searchForClient();

	void setView(ClientsView clientView);

	void updateClient();

	void goToAccountView();

}
