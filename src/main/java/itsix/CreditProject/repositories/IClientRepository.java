package itsix.CreditProject.repositories;

import java.io.Serializable;
import java.util.List;

import itsix.CreditProject.exceptions.ClientNotFoundException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;

public interface IClientRepository extends Serializable {

	void addNewClient(IClient client) throws ClientNotFoundException;

	IClient searchForClient(Integer searchSSN);

	IAccount giveDefaultAccount();

	void passDay();

	List<IClient> getClients();

}
