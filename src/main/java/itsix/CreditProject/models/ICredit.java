package itsix.CreditProject.models;

public interface ICredit {

	String getDescription();

	String getName();

	Double getMinValue();

	Double getMaxValue();

	Double getInterestRate();

	ICurrency getCurrency();

	Integer getPeriod();

	boolean hasCurrency(ICurrency currency);

	void updateFields(ICredit credit);

	IInterval getInterval();


}
