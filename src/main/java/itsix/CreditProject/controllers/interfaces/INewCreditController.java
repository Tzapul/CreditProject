package itsix.CreditProject.controllers.interfaces;

import java.util.List;

import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.views.NewCreditView;

public interface INewCreditController {

	List<IProduct> getCreditsList();

	void setView(NewCreditView view);

	void makeCredit();

	void setDescriptionText(String selectedProductDescription);

	void clearDescription();

}
