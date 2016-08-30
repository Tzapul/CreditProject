package itsix.CreditProject.controllers.implementations;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.CreditBuilder;
import itsix.CreditProject.builders.DaysPeriodBuilder;
import itsix.CreditProject.builders.ICreditBuilder;
import itsix.CreditProject.builders.IMoneyBuilder;
import itsix.CreditProject.builders.IPeriodBuilder;
import itsix.CreditProject.builders.IRateBuilder;
import itsix.CreditProject.builders.MoneyBuilder;
import itsix.CreditProject.builders.RateBuilder;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.IMakeCreditController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.IAccount;
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

	public AccountController(IRepository repository) {
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
		
		IMakeCreditController controller = new NewCreditController(repository, account, creditBuilder, creditValidator);
		
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

	@Override
	public void showWindow(IAccount account) {
		this.account = account;
		view.setVisible(true);
	}

}
