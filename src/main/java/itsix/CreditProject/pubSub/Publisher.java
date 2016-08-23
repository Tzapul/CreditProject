package itsix.CreditProject.pubSub;

import java.util.List;

public class Publisher implements IInnerPublisher {

	private List<ISubscriber> subscribers;

	public Publisher(List<ISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		subscribers.remove(subscriber);

	}

	@Override
	public void notifySubscribers() {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update();
		}
	}

}