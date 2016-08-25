package itsix.CreditProject.controllers;

import itsix.CreditProject.models.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;

public interface IClientBuilder {

	IClient build(Integer ssn, String firstname, String lastname, String address, IInnerPublisher publisher);

}
