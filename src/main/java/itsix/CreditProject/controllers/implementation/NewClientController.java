package itsix.CreditProject.controllers.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.IClientBuilder;
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

	private NewClientView newClientView;

	private IClientBuilder builder;

	private IClientValidator validator;

	public NewClientController(IClientRepository clientRepository, IClientBuilder builder, IClientValidator validator) {
		this.clientRepository = clientRepository;
		this.builder = builder;
		this.validator = validator;
	}

	@Override
	public void setView(NewClientView newClientView) {
		this.newClientView = newClientView;
	}

	@Override
	public void addClient() {
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);

		IClient client = builder.build(newClientView.getSSN(), newClientView.getFirstname(),
				newClientView.getLastname(), newClientView.getAddress(), publisher);

		IValidatorResult result = validator.validateFields(client);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		try {
			clientRepository.addNewClient(client);
			newClientView.dispose();
			newClientView.clear();
			JOptionPane.showMessageDialog(null, "Successfully created the client!", "Created",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ClientNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Client already exist!", "Not found", JOptionPane.WARNING_MESSAGE);
		}

	}

}
