package itsix.CreditProject.controllers.implementation;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.dispatcher.IDispatcher;
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

	private Map<Class<?>, IDispatcher> dispatchers;

	public NewCreditController(IRepository repository, IAccount account, ICreditBuilder creditBuilder,
			ICreditValidator validator, Map<Class<?>, IDispatcher> dispatchers) {
		this.repository = repository;
		this.account = account;
		this.creditBuilder = creditBuilder;
		this.validator = validator;
		this.dispatchers = dispatchers;
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

		IProduct selectedProduct = view.getSelectedProduct();
		IDispatcher dispatcher = dispatchers.get(selectedProduct.getClass());

		ICredit credit = creditBuilder.build(view.getCreditName(), view.getMoney(), view.getInterestRate(),
				view.getPeriod(), dispatcher.dispatch(selectedProduct));

		IValidatorResult result = validator.validateFields(credit, selectedProduct);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		selectedProduct.subscribe(credit);

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
