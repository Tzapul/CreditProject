package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.FixedCreditBuilder;
import itsix.CreditProject.builders.IntervalBuilder;
import itsix.CreditProject.builders.VariableCreditBuilder;
import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.Currency;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class CreditRepository implements ICreditRepository {

	private List<ICredit> credits;

	private IInnerPublisher publisher;
	
	public CreditRepository(IInnerPublisher publisher) {
		this.credits = new ArrayList<>();
		this.publisher = publisher;
	}

	@Override
	public void insertCredits(IRepository repository) {
		IntervalBuilder intervalBuilder = new IntervalBuilder();
		FixedCreditBuilder fixedCreditBuilder = new FixedCreditBuilder(intervalBuilder);
		VariableCreditBuilder variableCreditBuilder = new VariableCreditBuilder(intervalBuilder, repository);
		
		credits.add(fixedCreditBuilder.build("asdasdasd", 2000.0, 3000.0, 1.52, new Currency("USD", "$"), 12));
		credits.add(variableCreditBuilder.build("aseaseasease", 1000.0, 2500.0, 2.3, new Currency("EURO", "€"), 24));
	}

	@Override
	public List<ICredit> getCredits() {
		return credits;
	}

	@Override
	public void add(ICredit credit) {
		credits.add(credit);
		publisher.notifySubscribers();
	}

	@Override
	public void update(ICredit credit, ICredit updatedCredit) {
		for (ICredit myCredit : credits) {
			if(myCredit.equals(credit)) {
				myCredit.updateFields(updatedCredit);
			}
		}
		publisher.notifySubscribers();
	}

	@Override
	public void delete(ICredit credit) {
		for (ICredit myCredit : credits) {
			if(myCredit.equals(credit)) {
				credits.remove(myCredit);
				break;
			}
		}
		publisher.notifySubscribers();
	}

	@Override
	public void subscribe(ISubscriber subscriber) {
		publisher.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		publisher.unsubscribe(subscriber);
	}

}
