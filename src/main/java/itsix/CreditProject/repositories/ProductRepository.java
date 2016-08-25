package itsix.CreditProject.repositories;

import java.util.ArrayList;
import java.util.List;

import itsix.CreditProject.builders.FixedInterestProductBuilder;
import itsix.CreditProject.builders.IntervalBuilder;
import itsix.CreditProject.builders.VariableInterestProductBuilder;
import itsix.CreditProject.controllers.IRepository;
import itsix.CreditProject.models.Currency;
import itsix.CreditProject.models.IProduct;
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
		VariableInterestProductBuilder variableCreditBuilder = new VariableInterestProductBuilder(intervalBuilder, mainRepository);
		
		products.add(fixedCreditBuilder.build("asdasdasd", 2000, 3000, 1.52, new Currency("USD", "$"), 12, 24));
		products.add(variableCreditBuilder.build("aseaseasease", 1000, 2500, 2.3, new Currency("EURO", "€"), 6 , 24));
	}

	@Override
	public List<IProduct> getProducts() {
		return products;
	}

	@Override
	public void add(IProduct product) {
		products.add(product);
		publisher.notifySubscribers();
	}

	@Override
	public void update(IProduct product, IProduct updatedProduct) {
		for (IProduct myProduct : products) {
			if(myProduct.equals(product)) {
				myProduct.updateFields(updatedProduct);
			}
		}
		publisher.notifySubscribers();
	}

	@Override
	public void delete(IProduct product) {
		for (IProduct myProduct : products) {
			if(myProduct.equals(product)) {
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

}
