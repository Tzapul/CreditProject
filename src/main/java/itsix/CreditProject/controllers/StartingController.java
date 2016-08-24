package itsix.CreditProject.controllers;

import itsix.CreditProject.views.ClientsView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private IRepository repository;

	public StartingController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void goToProductsWindow() {

		ICreditsController controller = new ProductsController(repository);
		ProductsView creditView = new ProductsView(controller, repository);

		controller.setView(creditView);

		creditView.setVisible(true);
	}

	@Override
	public void goToClientsWindow() {
		IClientsController controller = new ClientsController(repository.getClientRepository());
		ClientsView clientView = new ClientsView(controller);
		clientView.setVisible(true);
		controller.setView(clientView);
	}

}
