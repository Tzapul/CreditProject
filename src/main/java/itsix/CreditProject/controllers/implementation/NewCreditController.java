package itsix.CreditProject.controllers.implementation;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.dispatcher.IDispatcher;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.NewCreditView;

public class NewCreditController implements INewCreditController {

	private static final long serialVersionUID = 1L;

	private IRepository repository;

	private NewCreditView newCreditView;
	private AccountView accountView;
	
	private IAccount account;

	private ICreditBuilder creditBuilder;

	private ICreditValidator validator;

	private Map<Class<?>, IDispatcher> dispatchers;

	public NewCreditController(IRepository repository, ICreditBuilder creditBuilder,
			ICreditValidator validator, Map<Class<?>, IDispatcher> dispatchers) {
		this.repository = repository;
		this.creditBuilder = creditBuilder;
		this.validator = validator;
		this.dispatchers = dispatchers;
	}

	@Override
	public List<IProduct> getCreditsList() {
		return repository.getProductRepository().getProductsWith(account.getCurrency());
	}

	@Override
	public void setView(NewCreditView newCreditView) {
		this.newCreditView = newCreditView;
	}
	
	@Override
	public void setAccountView(AccountView accountView) {
		this.accountView = accountView;
	}

	@Override
	public void makeCredit() {

		IProduct selectedProduct = newCreditView.getSelectedProduct();
		IDispatcher dispatcher = dispatchers.get(selectedProduct.getClass());

		ICredit credit = creditBuilder.build(newCreditView.getCreditName(), newCreditView.getMoney(),
				newCreditView.getInterestRate(), newCreditView.getPeriod(), dispatcher.dispatch(selectedProduct),
				account);

		IValidatorResult result = validator.validateFields(credit, selectedProduct);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}
		credit.subscribe(accountView);
		selectedProduct.subscribe(credit);

		account.addNew(credit);
		account.add(credit.getBorrowedMoney());
		newCreditView.setVisible(false);
		newCreditView.clear();

	}

	@Override
	public void setDescriptionText(String description) {
		newCreditView.setDescription(description);
	}

	@Override
	public void clearDescription() {
		newCreditView.clearDescription();
	}

	@Override
	public void setAccount(IAccount account) {
		this.account = account;
	}

}
