package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.IAccountBuilder;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.IClient;

public class ClientRepository implements IClientRepository {

	private List<IClient> clients;

	private IAccountBuilder accountBuilder;

	public ClientRepository(IAccountBuilder accountBuilder) {
		this.clients = new ArrayList<>();
		this.accountBuilder = accountBuilder;
	}

	@Override
	public void addNewClient(IClient client) {
		clients.add(client);
	}

	@Override
	public IClient searchForClient(Integer searchSSN) {
		for (IClient client : clients) {
			if (client.hasSSN(searchSSN)) {
				return client;
			}
		}

		return null;
	}

	@Override
	public IAccount giveDefaultAccount() {
		return accountBuilder.buildDefaultAccount();
	}

}
