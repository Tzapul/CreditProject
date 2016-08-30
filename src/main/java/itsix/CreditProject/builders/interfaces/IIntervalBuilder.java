package itsix.CreditProject.builders.interfaces;

import itsix.CreditProject.models.interfaces.IInterval;

public interface IIntervalBuilder {

	IInterval buildPeriodInterval(Integer min, Integer max);

	IInterval buildMoneyInterval(Integer min, Integer max);
}
