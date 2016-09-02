package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.implementations.FixedInterestProductBuilder;
import itsix.CreditProject.builders.implementations.IntervalBuilder;
import itsix.CreditProject.builders.implementations.VariableInterestProductBuilder;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.exceptions.ProductAlreadyExistsException;
import itsix.CreditProject.models.implementations.Currency;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class ProductRepository implements IProductRepository {

	private List<IProduct> products;

	private IInnerPublisher publisher;

	public ProductRepository(IInnerPublisher publisher) {
		this.products = new ArrayList<>();
		this.publisher = publisher;
	}

	@Override
	public void insertCredits(IRepository mainRepository) {
		IntervalBuilder intervalBuilder = new IntervalBuilder();
		FixedInterestProductBuilder fixedCreditBuilder = new FixedInterestProductBuilder(intervalBuilder);
		VariableInterestProductBuilder variableCreditBuilder = new VariableInterestProductBuilder(intervalBuilder,
				mainRepository);

		products.add(fixedCreditBuilder.build("asdasdasd", 2000, 3000, 1.52, new Currency("USD", "$"), 12, 24));
		products.add(variableCreditBuilder.build("aseaseasease", 1000, 2500, 2.3, new Currency("EURO", "€"), 6, 24));
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
	public void updateFixed(IProduct product, IProduct updatedProduct) {
		for (IProduct myProduct : products) {
			if (myProduct.equals(product)) {
				myProduct.updateFields(updatedProduct);
			}
		}
		publisher.notifySubscribers();
	}

	@Override
	public void updateVariable(IProduct product, IProduct updatedProduct) {
		for (IProduct myProduct : products) {
			if (myProduct.equals(product)) {
				myProduct.updateFields(updatedProduct);
			}
		}
		publisher.notifySubscribers();
	}

}
