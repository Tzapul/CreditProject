package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.pubSub.IInnerPublisher;

public interface IClientBuilder extends Serializable {

	IClient build(Integer ssn, String firstname, String lastname, String address, IInnerPublisher publisher);

}
