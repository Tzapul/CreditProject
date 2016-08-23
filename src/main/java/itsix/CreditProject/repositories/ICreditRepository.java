package itsix.CreditProject.repositories;

import java.util.List;

import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.pubSub.IPublisher;

public interface ICreditRepository extends IPublisher {

	List<ICredit> getCredits();

	void add(ICredit credit);

	void insertCredits(IRepository repository);

	void update(ICredit credit, ICredit updatedCredit);

	void delete(ICredit credit);

}
