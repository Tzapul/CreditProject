package itsix.CreditProject.builders;

import itsix.CreditProject.models.IInterval;

public interface IIntervalBuilder {

	IInterval buildPeriodInterval(Integer min, Integer max);

	IInterval buildMoneyInterval(Integer min, Integer max);
}
