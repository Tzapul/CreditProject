package itsix.CreditProject.controllers.implementation;

import java.util.List;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.NewCreditView;

public class NewCreditController implements INewCreditController {

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
		account.add(credit.getBorrowedMoney());
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
