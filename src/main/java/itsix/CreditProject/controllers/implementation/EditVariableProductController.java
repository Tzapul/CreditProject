package itsix.CreditProject.controllers.implementation;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.controllers.interfaces.IRepository;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.IEditProductView;

public class EditVariableProductController implements IEditVariableProductController {

	private IRepository repository;

	private IProduct product;

	private IEditProductView view;

	private IProductValidator productValidator;

	public EditVariableProductController(IRepository repository, IProductValidator productValidator) {
		this.repository = repository;
		this.productValidator = productValidator;
	}

	public void setProduct(IProduct product) {
		this.product = product;
		populateFields();
	}

	@Override
	public void updateCredit() {
		IProduct updatedCredit = view.getUpdatedProduct();

		IValidatorResult result = productValidator.validateFields(updatedCredit);

		if (result.isNotValid()) {
			JOptionPane.showMessageDialog(null, result.getDescription(), "Invalid Fields", JOptionPane.WARNING_MESSAGE);

			return;
		}

		repository.getProductRepository().updateProduct(product, updatedCredit);
		view.dispose();
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
	public String getIndicator() {
		return String.valueOf(repository.getIndicator());
	}

	@Override
	public void populateFields() {
		view.setCreditName(product.getName());
		view.setMinimumSize(product.getMinValue());
		view.setMaxValue(product.getMaxValue());
		
		System.out.println(repository.getIndicator().doubleValue());
		System.out.println(product.getInterestRate().doubleValue());
		System.out.println(product.getInterestRate().doubleValue() - repository.getIndicator().doubleValue());
		
		view.setInterestRate(product.getInterestRate().doubleValue() - repository.getIndicator().doubleValue());
		view.setCurrency(repository.getCurrencyRepository().getCreditIndex(product));
		view.setMinPeriod(product.getMinPeriod());
		view.setMaxPeriod(product.getMaxPeriod());
		view.assignInterestRateValue(product.getInterestRate().doubleValue());
	}

	@Override
	public Vector<ICurrency> getCurrencies() {
		return repository.getCurrencyRepository().getCurrencies();
	}

	@Override
	public void setView(IEditProductView view) {
		this.view = view;
	}

}
