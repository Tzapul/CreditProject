package itsix.CreditProject.builders.interfaces;

import java.io.Serializable;

import itsix.CreditProject.models.interfaces.IInterval;

public interface IIntervalBuilder extends Serializable {

	IInterval buildPeriodInterval(Integer min, Integer max);

	IInterval buildMoneyInterval(Integer min, Integer max);
}
