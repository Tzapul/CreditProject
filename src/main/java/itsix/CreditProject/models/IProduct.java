package itsix.CreditProject.models;

public interface IProduct {

	String getDescription();

	String getName();

	Integer getMinValue();

	Integer getMaxValue();

	Double getInterestRate();

	ICurrency getCurrency();

	IInterval getPeriod();

	boolean hasCurrency(ICurrency currency);

	void updateFields(IProduct credit);

	IInterval getInterval();

	Integer getMinPeriod();

	Integer getMaxPeriod();


}
