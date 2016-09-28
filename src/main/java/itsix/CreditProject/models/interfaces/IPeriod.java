package itsix.CreditProject.models.interfaces;

import java.io.Serializable;

public interface IPeriod extends Serializable {

	Integer getNumberOfDays();

	void decrement();

	boolean isZero();

}
