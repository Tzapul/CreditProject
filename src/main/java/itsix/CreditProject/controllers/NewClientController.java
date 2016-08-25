package itsix.CreditProject.controllers;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.models.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;
import itsix.CreditProject.pubSub.Publisher;
import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.views.NewClientView;

public class NewClientController implements INewClientController{

	private IClientRepository clientRepository;
	
	private NewClientView view;
	
	private IClientBuilder builder;

	public NewClientController(IClientRepository clientRepository, IClientBuilder builder) {
		super();
		this.clientRepository = clientRepository;
		this.builder = builder;
	}

	@Override
	public void setView(NewClientView view) {
		this.view = view;
	}

	@Override
	public void addClient() {
		List<ISubscriber> subscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		IClient client = builder.build(view.getSSN(), view.getFirstname(), view.getLastname(), view.getAddress(), publisher);
		
		clientRepository.addNewClient(client);
	}

}
