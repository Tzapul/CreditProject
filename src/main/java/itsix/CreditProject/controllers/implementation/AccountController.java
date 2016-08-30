package itsix.CreditProject.controllers.implementation;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.implementations.CreditBuilder;
import itsix.CreditProject.builders.implementations.DaysPeriodBuilder;
import itsix.CreditProject.builders.implementations.MoneyBuilder;
import itsix.CreditProject.builders.implementations.RateBuilder;
import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.validator.CreditValidator;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.NewCreditView;

public class AccountController implements IAccountController {

	private IRepository repository;

	private AccountView view;

	private IAccount account;

	public AccountController(IAccount account, IRepository repository) {
		this.account = account;
		this.repository = repository;
	}

	@Override
	public void setView(AccountView accountView) {
		this.view = accountView;
	}

	@Override
	public void updateFields() {
		view.setSold(account.getSold());
		view.setCurrency(account.getCurrencyName());
		view.setTableModel(account.getCredits());
	}

	@Override
	public void depositMoney() {
		account.deposit(view.getMoney());
	}

	@Override
	public IAccount getAccount() {
		return account;
	}

	@Override
	public void withdrawMoney() {
		try {
			account.withdraw(view.getMoney());
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void goToMakeCreditView() {

		IRateBuilder feeBuilder = new RateBuilder();
		IMoneyBuilder moneyBuilder = new MoneyBuilder();
		IPeriodBuilder periodBuilder = new DaysPeriodBuilder();
		ICreditBuilder creditBuilder = new CreditBuilder(moneyBuilder, periodBuilder, feeBuilder);

		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		ICreditValidator creditValidator = new CreditValidator(validator);

		INewCreditController controller = new NewCreditController(repository, account, creditBuilder, creditValidator);

		NewCreditView view = new NewCreditView(controller, this);

		view.setVisible(true);
		controller.setView(view);
	}

	@Override
	public void toggleOperationButtons() {
		if (view.moneyValueisZero()) {
			view.disableButtons();
		} else {
			view.enableButtons();
		}
	}

}
