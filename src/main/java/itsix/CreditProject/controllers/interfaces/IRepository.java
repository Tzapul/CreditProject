package itsix.CreditProject.controllers.interfaces;

import itsix.CreditProject.repositories.IClientRepository;
import itsix.CreditProject.repositories.IProductRepository;
import itsix.CreditProject.repositories.ICurrencyRepository;

public interface IRepository {

	Double getIndicator();

	IProductRepository getProductRepository();

	ICurrencyRepository getCurrencyRepository();

	IClientRepository getClientRepository();

	Integer getCurrentDay();

	void addDays(Integer days);


}
