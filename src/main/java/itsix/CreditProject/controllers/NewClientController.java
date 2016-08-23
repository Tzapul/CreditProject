package itsix.CreditProject.controllers;

import itsix.CreditProject.models.IClient;
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
		IClient client = builder.build(view.getSSN(), view.getFirstname(), view.getLastname(), view.getAddress());
		
		clientRepository.addNewClient(client);
	}

}
