package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           display.setText(String.valueOf(result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void processAC(ActionEvent event) {
        String ACButton = ((Button) event.getSource()).getText();
        System.out.println(ACButton);
        calculator = new Calculator();
        display.setText("0");
        number1 = 0;
        operator = "";
    }

    @FXML
    public void processDOT(ActionEvent event) {
        if ( ! display.getText().contains(".")) {
            double d = Double.parseDouble(display.getText());
            display.setText(String.format("%.0f.", d));
            System.out.println(".");
        }
    }

    @FXML
    public void processPM(ActionEvent event) {
        double number = calculator.calculate(Double.parseDouble(display.getText()), -1, "*");
        display.setText(String.valueOf(number));
        System.out.println("Changed current number's sign.");
    }
}
