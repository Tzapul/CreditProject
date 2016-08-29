package itsix.CreditProject.controllers;

import java.util.List;

import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.views.NewCreditView;

public interface IMakeCreditController {

	List<IProduct> getCreditsList();

	void setView(NewCreditView view);

	void makeCredit();

	void setDescriptionText(String selectedProductDescription);

	void clearDescription();

}
