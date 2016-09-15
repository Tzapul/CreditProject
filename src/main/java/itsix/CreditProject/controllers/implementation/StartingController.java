package itsix.CreditProject.controllers.implementation;

import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private ProductsView productsView;

	private ClientView clientsView;

	public StartingController(ProductsView productsView, ClientView clientsView) {
		this.productsView = productsView;
		this.clientsView = clientsView;
	}

	@Override
	public void showProductsWindow() {
		productsView.setVisible(true);
	}

	@Override
	public void showClientsWindow() {
		clientsView.setVisible(true);
	}

}
