package itsix.CreditProject.customs;

import java.math.BigDecimal;

public class KahanCalculator implements IKahanCalculator {

	private static final long serialVersionUID = 1L;

	/**
	 * Real Kahan sum that doesn't work!
	 */
	// @Override
	// public Double calculateSum(List<Double> inputs) {
	// Double sum = 0.0;
	// Double c = 0.0;
	//
	// for (Double input : inputs) {
	// Double y = input - c;
	// Double t = sum + y;
	// c = (t - sum) - y;
	// sum = t;
	// }
	//
	// return sum - c;
	// }

	@Override
	public Double calculateSubstraction(Double firstOperand, Double secondOperand) {

		BigDecimal first = new BigDecimal(String.valueOf(firstOperand));
		BigDecimal second = new BigDecimal(String.valueOf(secondOperand));

		return first.subtract(second).doubleValue();
	}

}
