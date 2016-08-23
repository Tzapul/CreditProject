package itsix.CreditProject.customs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class DoubleJTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public DoubleJTextField() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();

				if (validateNullField(ch)) {
					return;
				}

				if (!isNumber(ch) && !isValidSignal(ch) && !validatePoint(ch) && ch != '\b') {
					e.consume();
				}

				if(getText().length() >= 2) {
					if(isZeroWithPoint()) {
						System.out.println(getText());
						return;
					}
				}
				if (isNumber(ch) && prefixIsZero()) {
					setText(getText().substring(1));
				}

			}
		});

	}

	private boolean isZeroWithPoint() {
		return getText().substring(0, 2).equals("0.");
	}

	private boolean prefixIsZero() {
		return getText().substring(0, 1).equals("0");
	}

	private boolean validateNullField(char ch) {
		if (ch == '\b' && getText().length() == 0) {
			setText("0");
			return true;
		}

		return false;
	}

	private boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	private boolean isValidSignal(char ch) {
		if ((getText() == null || "".equals(getText().trim())) && ch == '-') {
			return true;
		}

		return false;
	}

	private boolean validatePoint(char ch) {
		if (ch != '.') {
			return false;
		}

		if (getText() == null || "".equals(getText().trim())) {
			setText("0.");
			return false;
		} else if ("-".equals(getText())) {
			setText("-0.");
		}

		return true;
	}
}
