package itsix.CreditProject.controllers.implementation;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.implementations.CreditBuilder;
import itsix.CreditProject.builders.implementations.DaysPeriodBuilder;
import itsix.CreditProject.builders.implementations.MoneyBuilder;
import itsix.CreditProject.builders.implementations.RateBuilder;
import itsix.CreditProject.builders.interfaces.ICreditBuilder;
import itsix.CreditProject.builders.interfaces.IMoneyBuilder;
import itsix.CreditProject.builders.interfaces.IOpertationBuilder;
import itsix.CreditProject.builders.interfaces.IPaymentBuilder;
import itsix.CreditProject.builders.interfaces.IPeriodBuilder;
import itsix.CreditProject.builders.interfaces.IRateBuilder;
import itsix.CreditProject.controllers.interfaces.IAccountController;
import itsix.CreditProject.controllers.interfaces.ICreditController;
import itsix.CreditProject.controllers.interfaces.INewCreditController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.dispatcher.FixedProductDispatcher;
import itsix.CreditProject.dispatcher.IDispatcher;
import itsix.CreditProject.dispatcher.VariableProductDispatcher;
import itsix.CreditProject.exceptions.SoldLesserThanZeroException;
import itsix.CreditProject.models.implementations.CashPayment;
import itsix.CreditProject.models.implementations.FixedInterestProduct;
import itsix.CreditProject.models.implementations.SoldPayment;
import itsix.CreditProject.models.implementations.VariableInterestProduct;
import itsix.CreditProject.models.interfaces.IAccount;
import itsix.CreditProject.models.interfaces.IClient;
import itsix.CreditProject.models.interfaces.ICredit;
import itsix.CreditProject.models.interfaces.IPayment;
import itsix.CreditProject.validator.CreditValidator;
import itsix.CreditProject.validator.ICreditValidator;
import itsix.CreditProject.validator.IValidator;
import itsix.CreditProject.validator.IValidatorResultBuilder;
import itsix.CreditProject.validator.Validator;
import itsix.CreditProject.validator.ValidatorResultBuilder;
import itsix.CreditProject.views.AccountView;
import itsix.CreditProject.views.CreditView;
import itsix.CreditProject.views.NewCreditView;

public class AccountController implements IAccountController {

	private IRepository repository;

	private AccountView view;

	private IAccount account;
	private IClient client;

	private IOpertationBuilder operationBuilder;
	private IPaymentBuilder paymentBuilder;
	
	public AccountController(IClient client, IAccount account, IRepository repository,
			IOpertationBuilder operationBuilder, IPaymentBuilder paymentBuilder) {
		this.client = client;
		this.repository = repository;
		this.operationBuilder = operationBuilder;
		this.account = account;
		this.paymentBuilder = paymentBuilder;
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
		client.addOperation(operationBuilder.buildDepositOperation(repository.getCurrentDay()));
	}

	@Override
	public IAccount getAccount() {
		return account;
	}

	@Override
	public void withdrawMoney() {
		try {
			account.withdraw(view.getMoney());
			client.addOperation(operationBuilder.buildWithdrawOperation(repository.getCurrentDay()));
		} catch (SoldLesserThanZeroException e) {
			JOptionPane.showMessageDialog(null, "Sold can't be lesser than 0!", null, JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void goToMakeCreditView() {

		ICreditBuilder creditBuilder = initializeCreditBuilder();

		ICreditValidator creditValidator = initializeCreditValidator();
		
		Map<Class<?>, IDispatcher> dispatchers = new HashMap<>();
		
		dispatchers.put(FixedInterestProduct.class, new FixedProductDispatcher());
		dispatchers.put(VariableInterestProduct.class, new VariableProductDispatcher());

		INewCreditController controller = new NewCreditController(repository, account, creditBuilder, creditValidator, dispatchers);

		NewCreditView view = new NewCreditView(controller, this);

		view.setVisible(true);
		controller.setView(view);
	}

	public ICreditBuilder initializeCreditBuilder() {
		
		IRateBuilder feeBuilder = new RateBuilder();
		IMoneyBuilder moneyBuilder = new MoneyBuilder();
		IPeriodBuilder periodBuilder = new DaysPeriodBuilder();
		ICreditBuilder creditBuilder = new CreditBuilder(moneyBuilder, periodBuilder, feeBuilder);
		
		return creditBuilder;
	}

	public ICreditValidator initializeCreditValidator() {
		
		StringBuilder errorMessageBuilder = new StringBuilder();
		IValidatorResultBuilder resultBuilder = new ValidatorResultBuilder();
		IValidator validator = new Validator(errorMessageBuilder, resultBuilder);
		ICreditValidator creditValidator = new CreditValidator(validator);
		
		return creditValidator;
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
	public void goToCreditView() {
		
		ICredit credit = view.getSelectedCredit();
		
		credit.subscribe(view);
		
		IPayment payment = paymentBuilder.build(credit);
		IPayment soldPayment = new SoldPayment(payment, account);
		IPayment cashPayment = new CashPayment(payment);
		
		ICreditController creditController = new CreditController(credit, cashPayment, soldPayment);
		
		CreditView creditView = new CreditView(creditController);
		
		creditView.setVisible(true);
		creditController.setView(creditView);
		creditController.populateFields();
	}

}
