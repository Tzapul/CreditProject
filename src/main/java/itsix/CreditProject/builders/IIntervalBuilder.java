package itsix.CreditProject.builders;

import itsix.CreditProject.models.IInterval;

public interface IIntervalBuilder {

	IInterval build(Double min, Double max);
}
