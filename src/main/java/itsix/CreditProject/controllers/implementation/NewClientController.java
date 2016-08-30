package itsix.CreditProject.controllers.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.IClientBuilder;
import itsix.CreditProject.controllers.interfaces.INewClientController;
import itsix.CreditProject.exceptions.ClientNotFoundException;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.validator.IClientValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.NewClientView;

public class NewClientController implements INewClientController {

	private IClientRepository clientRepository;

	private NewClientView view;

	private IClientBuilder builder;

	private IClientValidator validator;

	public NewClientController(IClientRepository clientRepository, IClientBuilder builder, IClientValidator validator) {
		super();
		this.clientRepository = clientRepository;
		this.builder = builder;
		this.validator = validator;
	}

	@Override
	public void setView(NewClientView view) {
		this.view = view;
	}

	@Override
	public void addClient() {
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		IClient client = builder.build(view.getSSN(), view.getFirstname(), view.getLastname(), view.getAddress(),
				publisher);

		IValidatorResult result = validator.validateFields(client);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		try {
			clientRepository.addNewClient(client);
			view.dispose();
		} catch (ClientNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Client already exists", "Not found", JOptionPane.WARNING_MESSAGE);
		}

	}

}
