package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
	@FXML
	private Label result;

	private Calculator cal = new Calculator();
	private boolean start = true;
	private String operator = null;
	private double num1;

	@FXML
	public void processNumber(ActionEvent event) {
		if (start) {
			showLabel("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		showLabel(result.getText() + value);
	}

	@FXML
	public void processOperator(ActionEvent event) {
		String value = ((Button) event.getSource()).getText();
		if (!value.equals("=")) {
			if (operator != null) {
				return;
			}
			operator = value;
			num1 = Double.parseDouble(result.getText());
			showLabel("");
		} else {
			if (operator == null) {
				return;
			}
			double num2 = Double.parseDouble(result.getText());
			double result = cal.Calculate(num1, num2, operator);
			String text = String.valueOf(result);
			if(result%1==0) {
				showLabel(String.format("%.0f", result));
			}else {
				showLabel(text);
			}
			reset();
		}
	}

	private void showLabel(String text) {
		result.setText(text);
	}

	public void reset() {
		operator = null;
		start = true;
	}
}
