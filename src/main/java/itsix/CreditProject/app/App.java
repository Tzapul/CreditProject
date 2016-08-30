package itsix.CreditProject.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import itsix.CreditProject.builders.implementations.AccountBuilder;
import itsix.CreditProject.builders.implementations.CurrencyBuilder;
import itsix.CreditProject.builders.interfaces.IAccountBuilder;
import itsix.CreditProject.builders.interfaces.ICurrencyBuilder;
import itsix.CreditProject.controllers.implementation.StartingController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.ClientRepository;
import itsix.CreditProject.repositories.ProductRepository;
import itsix.CreditProject.repositories.CurrencyRepository;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;
import itsix.CreditProject.repositories.IIndicator;
import itsix.CreditProject.repositories.Indicator;
import itsix.CreditProject.repositories.MainRepository;
import itsix.CreditProject.views.StartingView;

public class App extends JFrame {
	 
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ICurrencyRepository currencyRepository = new CurrencyRepository();
				
				IIndicator indicator = new Indicator(2.5);
				
				ICurrencyBuilder currencyBuilder = new CurrencyBuilder();
				IAccountBuilder accountBuilder = new AccountBuilder(currencyBuilder);
				IClientRepository clientRepository = new ClientRepository(accountBuilder);
				
				List<ISubscriber> subscribers = new ArrayList<>();
				IInnerPublisher publisher = new Publisher(subscribers);
				IProductRepository creditRepository = new ProductRepository(publisher);
				
				IRepository mainRepository = new MainRepository(creditRepository, currencyRepository, indicator, clientRepository);
				
				creditRepository.insertCredits(mainRepository);
				
				StartingController controller = new StartingController(mainRepository);
				StartingView view = new StartingView(controller);
				view.setVisible(true);
			}
		});
	}
}