package itsix.CreditProject.models.implementations;

import itsix.CreditProject.builders.implementations.IntervalBuilder;
import itsix.CreditProject.builders.implementations.ProductBuilder;
import itsix.CreditProject.builders.interfaces.IIntervalBuilder;
import itsix.CreditProject.builders.interfaces.IProductBuilder;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;

public class VariableInterestProduct implements IProduct {

	private IProduct product;

	private IIntervalBuilder intervalBuilder = new IntervalBuilder();
	private IProductBuilder creditBuilder = new ProductBuilder(intervalBuilder);

	public VariableInterestProduct(String name, IInterval interval, Double interestRate, ICurrency currency,
			IInterval periodInterval) {
		this.product = creditBuilder.build(name, interval.getMin(), interval.getMax(), interestRate, currency,
				periodInterval.getMin(), periodInterval.getMax());
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
	public Double getInterestRate() {
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

	@Override
	public boolean equals(Object obj) {
		return product.equals(obj);
	}

	@Override
	public String getType() {
		return "Variable";
	}
}
