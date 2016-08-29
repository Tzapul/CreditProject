package itsix.CreditProject.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.ICreditBuilder;
import itsix.CreditProject.models.IAccount;
import itsix.CreditProject.models.ICredit;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.NewCreditView;

public class NewCreditController implements IMakeCreditController {

	private IRepository repository;

	private NewCreditView view;

	private IAccount account;

	private ICreditBuilder creditBuilder;

	private ICreditValidator validator;

	public NewCreditController(IRepository repository, IAccount account, ICreditBuilder creditBuilder, ICreditValidator validator) {
		this.repository = repository;
		this.account = account;
		this.creditBuilder = creditBuilder;
		this.validator = validator;
	}

	@Override
	public List<IProduct> getCreditsList() {
		return repository.getProductRepository().getProductsWith(account.getCurrency());
	}

	@Override
	public void setView(NewCreditView view) {
		this.view = view;
	}

	@Override
	public void makeCredit() {
		ICredit credit = creditBuilder.build(view.getCreditName(), view.getMoney(), view.getInterestRate(),
				view.getPeriod());

		IValidatorResult result = validator.validateFields(credit, view.getSelectedProduct());

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		account.addNew(credit);
		view.dispose();

	}

	@Override
	public void setDescriptionText(String description) {
		view.setDescription(description);
	}

	@Override
	public void clearDescription() {
		view.clearDescription();
	}

}
