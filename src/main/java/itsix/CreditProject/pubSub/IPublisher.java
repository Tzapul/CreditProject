package itsix.CreditProject.pubSub;

import java.io.Serializable;

public interface IPublisher extends Serializable{
	
	public void subscribe(ISubscriber subscriber);

	public void unsubscribe(ISubscriber subscriber);
}
