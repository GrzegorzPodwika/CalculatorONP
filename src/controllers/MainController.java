package controllers;

import calculatorONP.CalculatorONP;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController {

    // references to objects
    @FXML
    public Label label_equation;

    @FXML
    public Label label_history;

    @FXML
    public Label label_correctness;

    @FXML
    public ImageView iV_factorial;

    @FXML
    public ImageView iV_left_parenthesis;

    @FXML
    public ImageView iV_right_parenthesis;

    @FXML
    public ImageView iV_delete_one_char;

    @FXML
    public ImageView iV_modulo;

    @FXML
    public ImageView iv_nth_root;

    @FXML
    public ImageView iV_exponentiation;

    @FXML
    public ImageView iV_division;

    @FXML
    public ImageView iV_dot;

    @FXML
    public ImageView id_calculate;

    @FXML
    public ImageView iV_addition;

    @FXML
    public ImageView iV_multiplication;

    @FXML
    public ImageView iV_clean_equation;

    @FXML
    public ImageView iV_subtraction;

    @FXML
    public Text text_seven;

    @FXML
    public Text text_eight;

    @FXML
    public Text text_nine;

    @FXML
    public Text text_four;

    @FXML
    public Text text_five;

    @FXML
    public Text text_six;

    @FXML
    public Text text_one;

    @FXML
    public Text text_two;

    @FXML
    public Text text_three;

    @FXML
    public Text text_zero;
    // end

    private final StringBuilder equationBuilder = new StringBuilder();
    private final CalculatorONP calculatorONP = new CalculatorONP();
    private String equationResult;

    public MainController() { }

    // Digits methods
    public void onZeroClick(MouseEvent mouseEvent) {
        equationBuilder.append('0');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onOneClick(MouseEvent mouseEvent) {
        equationBuilder.append('1');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onTwoClick(MouseEvent mouseEvent) {
        equationBuilder.append('2');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onThreeClick(MouseEvent mouseEvent) {
        equationBuilder.append('3');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onFourClick(MouseEvent mouseEvent) {
        equationBuilder.append('4');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onFiveClick(MouseEvent mouseEvent) {
        equationBuilder.append('5');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onSixClick(MouseEvent mouseEvent) {
        equationBuilder.append('6');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onSevenClick(MouseEvent mouseEvent) {
        equationBuilder.append('7');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onEightClick(MouseEvent mouseEvent) {
        equationBuilder.append('8');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onNineClick(MouseEvent mouseEvent) {
        equationBuilder.append('9');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    // Signs methods
    public void onFactorialClick(MouseEvent mouseEvent) {
        equationBuilder.append('!');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }
    public void onLeftParenthesisClick(MouseEvent mouseEvent) {
        equationBuilder.append('(');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onRightParenthesisClick(MouseEvent mouseEvent) {
        equationBuilder.append(')');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onDeleteOneCharClick(MouseEvent mouseEvent) {
        if (equationBuilder.length() != 0)
            equationBuilder.deleteCharAt(equationBuilder.length() - 1);

        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onModuloClick(MouseEvent mouseEvent) {
        equationBuilder.append('%');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onTakeNthRootClick(MouseEvent mouseEvent) {
        equationBuilder.append('#');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onRaiseToPowerClick(MouseEvent mouseEvent) {
        equationBuilder.append('^');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onDivideClick(MouseEvent mouseEvent) {
        equationBuilder.append('/');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onMultiplyClick(MouseEvent mouseEvent) {
        equationBuilder.append('*');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onSubtractClick(MouseEvent mouseEvent) {
        equationBuilder.append('-');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onAddClick(MouseEvent mouseEvent) {
        equationBuilder.append('+');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onClearClick(MouseEvent mouseEvent) {
        clearBuilder();
        clearLabels();
    }

    private void clearBuilder(){
        equationBuilder.delete(0, equationBuilder.length());
    }

    private void clearLabels(){
        clearMainLabel();
        clearHistoryLabel();
    }

    private void clearMainLabel(){
        label_equation.setText("");
        label_correctness.setText("CORRECT");
    }

    private void clearHistoryLabel(){
        label_history.setText("");
    }

    public void onDotClick(MouseEvent mouseEvent) {
        equationBuilder.append('.');
        label_equation.setText(equationBuilder.toString());
        checkTheCorrectnessOfEquation();
    }

    public void onCalculateClick(MouseEvent mouseEvent) {
        calculateEquation();
    }

    private void calculateEquation() {
        calculate();

        clearMainLabel();
        clearBuilder();
        checkIfTheResultIsError();
    }

    private void calculate() {
        equationResult = calculatorONP.calculateInfixEquation(equationBuilder.toString());
        label_history.setText(equationResult);
    }

    private void checkIfTheResultIsError() {
        if (!equationResult.equals(CalculatorONP.ERROR_MESSAGE))
            equationBuilder.append(equationResult);
    }

    private void checkTheCorrectnessOfEquation() {
        boolean isCorrectEquation = calculatorONP.isTheEquationCorrect(equationBuilder.toString());
        
        if (isCorrectEquation)
            label_correctness.setText("CORRECT");
        else
            label_correctness.setText("ERROR");
    }
}
