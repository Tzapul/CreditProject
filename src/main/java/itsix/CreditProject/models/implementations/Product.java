package itsix.CreditProject.models.implementations;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IInterval;
import itsix.CreditProject.models.interfaces.IProduct;

public class Product implements IProduct {

	private String name;

	private IInterval moneyInterval;
	private ICurrency currency;

	private Double interestRate;
	private IInterval periodInterval;

	public Product(String name, IInterval interval, ICurrency currency, Double interestRate, IInterval period) {
		super();
		this.name = name;
		this.moneyInterval = interval;
		this.currency = currency;
		this.interestRate = interestRate;
		this.periodInterval = period;
	}

	@Override
	public String getDescription() {
		StringBuilder builder = new StringBuilder();

		appendName(builder);
		appendInterval(builder);
		appendCurrency(builder);
		appendInterestRate(builder);
		appendPeriod(builder);
		return builder.toString();
	}

	private void appendPeriod(StringBuilder builder) {
		builder.append("Period : " + periodInterval.getMin() + "-" + periodInterval.getMax() + " (months)");
		builder.append("\n");
	}

	private void appendInterestRate(StringBuilder builder) {
		builder.append("Interest rate : " + interestRate + "%");
		builder.append("\n");
	}

	private void appendCurrency(StringBuilder builder) {
		builder.append("Currency : " + currency.getName() + " (" + currency.getSymbol() + ")");
		builder.append("\n");
	}

	private void appendInterval(StringBuilder builder) {
		builder.append(
				"Money interval : " + moneyInterval.getMin().intValue() + " - " + moneyInterval.getMax().intValue());
		builder.append("\n");
	}

	private void appendName(StringBuilder builder) {
		builder.append("Product name : " + name);
		builder.append("\n");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getMinValue() {
		return moneyInterval.getMin();
	}

	@Override
	public Integer getMaxValue() {
		return moneyInterval.getMax();
	}

	@Override
	public Double getInterestRate() {
		return interestRate;
	}

	@Override
	public ICurrency getCurrency() {
		return currency;
	}

	@Override
	public IInterval getPeriodInterval() {
		return periodInterval;
	}

	@Override
	public IInterval getMoneyInterval() {
		return moneyInterval;
	}

	@Override
	public boolean hasCurrency(ICurrency currency) {
		return currency.equals(this.currency);
	}

	@Override
	public void updateFields(IProduct product) {
		this.name = product.getName();
		this.periodInterval = product.getPeriodInterval();
		this.interestRate = product.getInterestRate();
		this.currency = product.getCurrency();
		this.moneyInterval = product.getMoneyInterval();
	}

	@Override
	public Integer getMinPeriod() {
		return periodInterval.getMin();
	}

	@Override
	public Integer getMaxPeriod() {
		return periodInterval.getMax();
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (interestRate == null) {
			if (other.interestRate != null)
				return false;
		} else if (!interestRate.equals(other.interestRate))
			return false;
		if (moneyInterval == null) {
			if (other.moneyInterval != null)
				return false;
		} else if (!moneyInterval.equals(other.moneyInterval))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (periodInterval == null) {
			if (other.periodInterval != null)
				return false;
		} else if (!periodInterval.equals(other.periodInterval))
			return false;
		return true;
	}

}
