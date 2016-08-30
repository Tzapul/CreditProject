package itsix.CreditProject.controllers.implementation;

import itsix.CreditProject.controllers.interfaces.IClientsController;
import itsix.CreditProject.controllers.interfaces.IProductsController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private IRepository repository;

	public StartingController(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void goToProductsWindow() {

		IProductsController controller = new ProductsController(repository);
		ProductsView creditView = new ProductsView(controller, repository);

		controller.setView(creditView);

		creditView.setVisible(true);
	}

	@Override
	public void goToClientsWindow() {
		IClientsController controller = new ClientsController(repository.getCurrencyRepository(), repository);
		ClientView clientView = new ClientView(controller);
		clientView.setVisible(true);
		controller.setView(clientView);
	}

}
