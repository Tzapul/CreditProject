package itsix.CreditProject.builders.implementations;

import itsix.CreditProject.builders.interfaces.IClientBuilder;
import itsix.CreditProject.models.implementations.Client;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.repositories.IClientRepository;

public class ClientBuilder implements IClientBuilder{

	
	private IClientRepository clientRepository;

	public ClientBuilder(IClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@Override
	public IClient build(Integer ssn, String firstname, String lastname, String address, IInnerPublisher publisher) {
		
		IClient client = new Client(ssn, firstname, lastname, address, publisher);
		
		IAccount defaultAccount = clientRepository.giveDefaultAccount();
		
		client.addAccount(defaultAccount);
		
		return client;
	}

}
