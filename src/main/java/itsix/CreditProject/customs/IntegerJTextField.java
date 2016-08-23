package itsix.CreditProject.customs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class IntegerJTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public IntegerJTextField() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();

				if (validateNullField(ch)) {
					return;
				}
				
				if (!isNumber(ch) && !isValidSignal(ch) && ch != '\b') {
					e.consume();
				}
				
				if (isNumber(ch) && prefixIsZero()) {
					setText(getText().substring(1));
				}
			}
		});

	}
	
	private boolean validateNullField(char ch) {
		if (ch == '\b' && getText().length() == 0) {
			setText("0");
			return true;
		}

		return false;
	}
	
	private boolean prefixIsZero() {
		return getText().substring(0, 1).equals("0");
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

}
