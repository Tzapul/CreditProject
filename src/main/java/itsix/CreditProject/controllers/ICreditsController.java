package itsix.CreditProject.controllers;

import java.util.List;

import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.views.CreditsView;

public interface ICreditsController {

	void goToNewCreditView();

	List<ICredit> getCreditsList();

	void setDescriptionText(String description);

	void setView(CreditsView view);

	void goToEditProduct();

	void delete(ICredit credit);

	void clearDescription();
}
