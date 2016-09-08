package itsix.CreditProject.repositories;

import itsix.CreditProject.exceptions.ClientNotFoundException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;

public interface IClientRepository {

	void addNewClient(IClient client) throws ClientNotFoundException;

	IClient searchForClient(Integer searchSSN);

	IAccount giveDefaultAccount();

	void passDay();


}
