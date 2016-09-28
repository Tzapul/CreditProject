package itsix.CreditProject.controllers.interfaces;

import java.io.Serializable;
import java.util.List;

import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.NewCreditView;

public interface INewCreditController extends Serializable {

	List<IProduct> getCreditsList();

	void setView(NewCreditView view);

	void makeCredit();

	void setDescriptionText(String selectedProductDescription);

	void clearDescription();

	void setAccountView(AccountView accountView);

	void setAccount(IAccount account);

}
