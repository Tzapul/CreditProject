package itsix.CreditProject.controllers.implementation;

import java.io.IOException;

import itsix.CreditProject.controllers.interfaces.IStartingController;
import itsix.CreditProject.customs.RepositoryParser;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.views.ClientView;
import itsix.CreditProject.views.ProductsView;

public class StartingController implements IStartingController {

	private static final long serialVersionUID = 1L;

	private ProductsView productsView;

	private ClientView clientsView;

	private RepositoryParser serializer;

	private IRepository mainRepository;

	public StartingController(ProductsView productsView, ClientView clientsView, RepositoryParser serializer,
			IRepository mainRepository) {
		this.productsView = productsView;
		this.clientsView = clientsView;
		this.serializer = serializer;
		this.mainRepository = mainRepository;
	}

	@Override
	public void showProductsWindow() {
		productsView.setVisible(true);
	}

	@Override
	public void showClientsWindow() {
		clientsView.setVisible(true);
	}

	@Override
	public void serialize() {
		try {

//			try {
//				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//			} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException
//					| IllegalAccessException e) {
//				e.printStackTrace();
//			}

			serializer.serialize(mainRepository);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

}
