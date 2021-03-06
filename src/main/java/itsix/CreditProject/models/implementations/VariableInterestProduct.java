package itsix.CreditProject.models.implementations;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.builders.implementations.IntervalBuilder;
import itsix.CreditProject.builders.implementations.ProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IProductBuilder;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.pubSub.IInnerPublisher;
import itsix.CreditProject.pubSub.ISubscriber;

public class VariableInterestProduct implements IProduct {

	private static final long serialVersionUID = 1L;

	private IProduct product;

	private IIntervalBuilder intervalBuilder = new IntervalBuilder();
	private IProductBuilder creditBuilder = new ProductBuilder(intervalBuilder);

	public VariableInterestProduct(String name, IInterval interval, MutableDouble interestRate, ICurrency currency,
			IInterval periodInterval, IInnerPublisher publisher) {
		this.product = creditBuilder.build(name, interval.getMin(), interval.getMax(), interestRate, currency,
				periodInterval.getMin(), periodInterval.getMax(), publisher);
	}

	@Override
	public String getDescription() {
		return product.getDescription();
	}

	@Override
	public String getName() {
		return product.getName();
	}

	@Override
	public Integer getMinValue() {
		return product.getMinValue();
	}

	@Override
	public Integer getMaxValue() {
		return product.getMaxValue();
	}

	@Override
	public MutableDouble getInterestRate() {
		return product.getInterestRate();
	}

	@Override
	public ICurrency getCurrency() {
		return product.getCurrency();
	}

	@Override
	public IInterval getPeriodInterval() {
		return product.getPeriodInterval();
	}

	@Override
	public String toString() {
		return product.getName();
	}

	@Override
	public boolean hasCurrency(ICurrency currency) {
		return product.hasCurrency(currency);
	}

	@Override
	public void updateFields(IProduct credit) {
		this.product.updateFields(credit);
	}

	@Override
	public IInterval getMoneyInterval() {
		return product.getMoneyInterval();
	}

	@Override
	public Integer getMinPeriod() {
		return product.getMinPeriod();
	}

	@Override
	public Integer getMaxPeriod() {
		return product.getMaxPeriod();
	}
	
	public IProduct getProduct() {
		return product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableInterestProduct other = (VariableInterestProduct) obj;

		if (product == null) {
			if (other.getProduct() != null)
				return false;
		} else if (!product.equals(other.getProduct()))
			return false;
		return true;
	}

	@Override
	public String getType() {
		return "Variable";
	}
	
	@Override
	public void subscribe(ISubscriber subscriber) {
		product.subscribe(subscriber);
	}

	@Override
	public void unsubscribe(ISubscriber subscriber) {
		product.unsubscribe(subscriber);
	}
}
