package itsix.CreditProject.controllers;

import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;

public interface IRepository {

	Double getIndicator();

	IProductRepository getCreditRepository();

	ICurrencyRepository getCurrencyRepository();

	IClientRepository getClientRepository();


}
