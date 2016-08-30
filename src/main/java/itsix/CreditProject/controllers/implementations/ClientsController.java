package itsix.CreditProject.controllers.implementations;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.models.IClient;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.views.ClientsView;

public class ClientsController implements IClientsController {

	private IRepository repository;

	private ICurrencyRepository currencyRepository;

	private ClientsView view;

	private IClient currentClient;

	public ClientsController(ICurrencyRepository currencyRepository, IRepository repository) {
		this.currencyRepository = currencyRepository;
		this.repository = repository;
	}

	public void setView(ClientsView view) {
		this.view = view;
	}

	@Override
	public void searchForClient() {

		try {
			currentClient = repository.getClientRepository().searchForClient(view.getSearchSSN());

			view.subscribe();
			view.paintClient(currentClient);
			view.clearSearchTextField();
			view.setUpdateClientEnabled();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Client not found!", null, JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void updateClient() {
		currentClient.update(view.getFirstname(), view.getLastname(), view.getAddress());
	}

	@Override
	public void updateTableModel() {
		view.paintClient(currentClient);
	}

	@Override
	public IClient getCurrentClient() {
		return currentClient;
	}

	@Override
	public void hasAllAccounts() {
		if (currencyRepository.hasAllCurrenciesOf(currentClient)) {
			view.setNewAccountDisabled();
		} else {
			view.setNewAccountEnabled();
		}
	}

	@Override
	public void showWindow() {
		view.setVisible(true);
	}

}
