package itsix.CreditProject.repositories;

import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.IClient;

public interface IClientRepository {

	void addNewClient(IClient client);

	IClient searchForClient(Integer searchSSN);

	IAccount giveDefaultAccount();

}
