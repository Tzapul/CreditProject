package itsix.CreditProject.customs;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import itsix.CreditProject.models.interfaces.IAccount;

public class AccountTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Currency", "Symbol", "Sold"};

	private List<IAccount> data;

	public AccountTableModel(List<IAccount> data) {
		super();
		this.data = data;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getCurrencyName();
		case 1:
			return data.get(row).getSymbol();
		case 2:
			return data.get(row).getSold();
		}

		return null;
	}

	public IAccount getRow(Integer row) {
		return data.get(row);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
}
