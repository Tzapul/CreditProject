package itsix.CreditProject.controllers.implementation;

import java.util.Vector;

import javax.swing.JOptionPane;

import itsix.CreditProject.controllers.interfaces.IEditVariableProductController;
import itsix.CreditProject.customs.IKahanCalculator;
import itsix.CreditProject.models.interfaces.ICurrency;
import itsix.CreditProject.models.interfaces.IProduct;
import itsix.CreditProject.repositories.IRepository;
import itsix.CreditProject.validator.IProductValidator;
import itsix.CreditProject.validator.IValidatorResult;
import itsix.CreditProject.views.IEditProductView;

public class EditVariableProductController implements IEditVariableProductController {

	private static final long serialVersionUID = 1L;

	private IRepository repository;

	private IProduct product;

	private IEditProductView view;

	private IProductValidator productValidator;
	
	private IKahanCalculator kahanCalculator;

	public EditVariableProductController(IRepository repository, IProductValidator productValidator, IKahanCalculator kahanCalculator) {
		this.repository = repository;
		this.productValidator = productValidator;
		this.kahanCalculator = kahanCalculator;
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
		view.setVisible(false);
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
		
//		System.out.println(product.getInterestRate().toDouble());
//		System.out.println(repository.getIndicator());
//		System.out.println((product.getInterestRate().toDouble() - repository.getIndicator()));
		
		view.setInterestRate(kahanCalculator.calculateSubstraction(product.getInterestRate().toDouble(), repository.getIndicator()));
		
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
