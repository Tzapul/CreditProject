package itsix.CreditProject.controllers;

import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.ICreditRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;

public interface IRepository {

	Double getIndicator();

	ICreditRepository getCreditRepository();

	ICurrencyRepository getCurrencyRepository();

	IClientRepository getClientRepository();


}
