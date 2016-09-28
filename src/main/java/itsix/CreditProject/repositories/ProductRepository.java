package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.exceptions.ProductAlreadyExistsException;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class ProductRepository implements IProductRepository {

	private static final long serialVersionUID = 1L;

	private List<IProduct> products;

	private IInnerPublisher publisher;

	public ProductRepository(IInnerPublisher publisher) {
		this.products = new ArrayList<>();
		this.publisher = publisher;
	}

	@Override
	public List<IProduct> getProducts() {
		return products;
	}

	@Override
	public void add(IProduct product) throws ProductAlreadyExistsException {
		searchFor(product);
		products.add(product);
		publisher.notifySubscribers();
	}

	private void searchFor(IProduct product) throws ProductAlreadyExistsException {
		for (IProduct myProduct : products) {
			if (myProduct.equals(product)) {
				throw new ProductAlreadyExistsException();
			}
		}
	}

	@Override
	public void delete(IProduct product) {
		for (IProduct myProduct : products) {
			if (myProduct.equals(product)) {
				products.remove(myProduct);
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

	@Override
	public List<IProduct> getProductsWith(ICurrency currency) {
		List<IProduct> toReturn = new ArrayList<>();

		for (IProduct product : products) {
			if (product.hasCurrency(currency)) {
				toReturn.add(product);
			}
		}
		return toReturn;
	}

	@Override
	public void updateProduct(IProduct product, IProduct updatedProduct) {
		for (IProduct myProduct : products) {
			if (myProduct.equals(product)) {
				myProduct.updateFields(updatedProduct);
			}
		}
		publisher.notifySubscribers();
	}

	@Override
	public boolean hasNoCredits() {
		return products.size() == 0;
	}

}
