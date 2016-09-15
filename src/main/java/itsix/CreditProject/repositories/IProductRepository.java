package itsix.CreditProject.repositories;

import java.util.List;

import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.exceptions.ProductAlreadyExistsException;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IPublisher;

public interface IProductRepository extends IPublisher {

	List<IProduct> getProducts();

	void add(IProduct credit) throws ProductAlreadyExistsException;

	void insertCredits(IRepository repository);

	void delete(IProduct credit);

	List<IProduct> getProductsWith(ICurrency currency);

	void updateProduct(IProduct product, IProduct updatedProduct);

	boolean hasNoCredits();

}
