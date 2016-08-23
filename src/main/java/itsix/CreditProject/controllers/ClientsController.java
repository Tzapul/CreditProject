package itsix.CreditProject.controllers;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.AccountBuilder;
import itsix.CreditProject.builders.ClientBuilder;
import itsix.CreditProject.builders.CurrencyBuilder;
import itsix.CreditProject.builders.IAccountBuilder;
import itsix.CreditProject.builders.ICurrencyBuilder;
import itsix.CreditProject.models.Client;
import itsix.CreditProject.models.IClient;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.views.ClientsView;
import itsix.CreditProject.views.NewClientView;

public class ClientsController implements IClientsController {

	private IClientRepository clientRepository;

	private ClientsView view;

	private IClient currentClient;

	public ClientsController(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public void goToNewClientView() {

		ICurrencyBuilder currencyBuilder = new CurrencyBuilder();
		IAccountBuilder accountBuilder = new AccountBuilder(currencyBuilder);

		IClientBuilder clientBuilder = new ClientBuilder(accountBuilder, clientRepository);

		INewClientController controller = new NewClientController(clientRepository, clientBuilder);

		NewClientView newClientView = new NewClientView(controller);

		newClientView.setVisible(true);
		controller.setView(newClientView);

	}

	public void setView(ClientsView view) {
		this.view = view;
	}

	@Override
	public void searchForClient() {

		try {
			currentClient = clientRepository.searchForClient(view.getSearchSSN());

			view.paintClient(currentClient);
			view.clearSearchTextField();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Client not found!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void updateClient() {
		currentClient.update(view.getFirstname(), view.getLastname(), view.getAddress());
	}

}
