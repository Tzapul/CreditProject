package itsix.CreditProject.repositories;

import java.io.Serializable;

public interface IRepository extends Serializable {

	Double getIndicator();

	IProductRepository getProductRepository();

	ICurrencyRepository getCurrencyRepository();

	IClientRepository getClientRepository();

	Integer getCurrentDay();

	void addDays(Integer days);

}
