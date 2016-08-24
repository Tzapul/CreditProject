package itsix.CreditProject.repositories;

import java.util.List;

import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.pubSub.IPublisher;

public interface IProductRepository extends IPublisher {

	List<IProduct> getProducts();

	void add(IProduct credit);

	void insertCredits(IRepository repository);

	void update(IProduct credit, IProduct updatedCredit);

	void delete(IProduct credit);

}
