package itsix.CreditProject.controllers.implementations;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.builders.IProductBuilder;
import itsix.CreditProject.controllers.interfaces.INewProductController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.exceptions.ProductAlreadyExistsException;
import itsix.CreditProject.models.ICurrency;
import itsix.CreditProject.models.IProduct;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.NewProductView;

public class NewProductController implements INewProductController {

	private IRepository repository;

	private NewProductView view;

	private IProductBuilder fixedInterestBuilder;
	private IProductBuilder variableInterestBuilder;
	private IProductBuilder currentBuilder;

	private IProductValidator validator;

	public NewProductController(IRepository repository, IProductBuilder fixedInterestBuilder,
			IProductBuilder variableInterestBuilder, IProductValidator validator) {

		this.repository = repository;
		this.fixedInterestBuilder = fixedInterestBuilder;
		this.variableInterestBuilder = variableInterestBuilder;
		this.validator = validator;

		changeToFixedBuilder();
	}

	@Override
	public void createNewCredit() {
		IProduct product = currentBuilder.build(view.getCreditName(), view.getMinValue(), view.getMaxValue(),
				view.getInterestRate(), view.getCurrency(), view.getMinPeriod(), view.getMaxPeriod());

		IValidatorResult result = validator.validateFields(product);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		try {
			repository.getProductRepository().add(product);
			view.dispose();
		} catch (ProductAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, "Product already exists", "Client", JOptionPane.WARNING_MESSAGE);

		}

	}

	@Override
	public Vector<ICurrency> getCurrencies() {
		return repository.getCurrencyRepository().getCurrencies();
	}

	@Override
	public void setView(NewProductView newCreditView) {
		this.view = newCreditView;
	}

	@Override
	public void changeToFixedBuilder() {
		this.currentBuilder = fixedInterestBuilder;
	}

	@Override
	public void changeToVariableBuilder() {
		this.currentBuilder = variableInterestBuilder;
	}

	@Override
	public void setLabelsVisible() {
		view.setInterestRateVisible();
		view.setInterestRateValueVisible();

		if (view.interestIsNotNull()) {
			updateValue();
		}
	}

	@Override
	public void setLabelsInvisible() {
		view.setInterestRateInvisible();
		view.setInterestRateValueInvisible();
	}

	@Override
	public void updateRealInterestRate() {
		updateValue();
	}

	public void updateValue() {
		Double value = repository.getIndicator() + view.getInterestRate();
		view.assignInterestRateValue(value);
	}

	@Override
	public void showWindow() {
		view.setVisible(true);
	}

}
