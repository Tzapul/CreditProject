package itsix.CreditProject.models;

public interface IProduct {

	String getDescription();

	String getName();

	Integer getMinValue();

	Integer getMaxValue();

	Double getInterestRate();

	ICurrency getCurrency();

	IInterval getPeriodInterval();

	boolean hasCurrency(ICurrency currency);

	void updateFields(IProduct credit);

	IInterval getMoneyInterval();

	Integer getMinPeriod();

	Integer getMaxPeriod();

}
