package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;

public interface IClientBuilder {

	IClient build(Integer ssn, String firstname, String lastname, String address, IInnerPublisher publisher);

}
