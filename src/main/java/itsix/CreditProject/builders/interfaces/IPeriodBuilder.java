package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IPeriod;

public interface IPeriodBuilder extends Serializable {

	IPeriod build(Integer period);

}
