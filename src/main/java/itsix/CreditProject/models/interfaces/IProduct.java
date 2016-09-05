package itsix.CreditProject.models.interfaces;

import org.apache.commons.lang.mutable.MutableDouble;

import itsix.CreditProject.pubSub.IPublisher;

public interface IProduct extends IPublisher {

	String getDescription();

	String getName();

	Integer getMinValue();

	Integer getMaxValue();

	MutableDouble getInterestRate();

	ICurrency getCurrency();

	IInterval getPeriodInterval();

	boolean hasCurrency(ICurrency currency);

	void updateFields(IProduct credit);

	IInterval getMoneyInterval();

	Integer getMinPeriod();

	Integer getMaxPeriod();

	String getType();

}
