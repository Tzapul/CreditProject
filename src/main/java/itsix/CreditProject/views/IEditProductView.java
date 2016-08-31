package itsix.CreditProject.views;

import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;

public interface IEditProductView {

	IProduct getUpdatedProduct();

	void dispose();

	void setCreditName(String name);

	void setMinimumSize(Integer minValue);

	void setMaxValue(Integer maxValue);

	void setInterestRate(Double interestRate);

	void setCurrency(ICurrency creditIndex);

	void setMinPeriod(Integer minPeriod);

	void setMaxPeriod(Integer maxPeriod);

	void setVisible(boolean b);

	void setControllerProduct(IProduct product);

	Double getInterestRate();

	void assignInterestRateValue(Double value);

}
