package itsix.CreditProject.builders;

import itsix.CreditProject.controllers.IClientBuilder;
import itsix.CreditProject.models.Client;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.IClient;
import itsix.CreditProject.repositories.IClientRepository;

public class ClientBuilder implements IClientBuilder {

	private IAccountBuilder builder;
	
	private IClientRepository clientRepository;

	public ClientBuilder(IAccountBuilder builder, IClientRepository clientRepository) {
		super();
		this.builder = builder;
		this.clientRepository = clientRepository;
	}

	@Override
	public IClient build(Integer ssn, String firstname, String lastname, String address) {
		IClient client = new Client(ssn, firstname, lastname, address);
		
		IAccount defaultAccount = clientRepository.giveDefaultAccount();
		
		client.addAccount(defaultAccount);
		
		return client;
	}

}
