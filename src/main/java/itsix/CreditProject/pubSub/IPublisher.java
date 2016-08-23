package itsix.CreditProject.pubSub;

public interface IPublisher {
	
	public void subscribe(ISubscriber subscriber);

	public void unsubscribe(ISubscriber subscriber);
}
