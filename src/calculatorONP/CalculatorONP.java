package calculatorONP;

import operators.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.function.Function;

/**
 * @author Grzegorz Podwika
 * Class representing calculator from Infix Notation to Postfix Notation.
 */
public class CalculatorONP {
    private final ListStack<Operator> operatorStack = new ListStack<>();
    private final ListStack<String> numberStack = new ListStack<>();
    private final Equation equation = new Equation();
    private final StringBuilder onpBuilder = new StringBuilder();
    public static final String ERROR_MESSAGE = "ERROR";

    /**
     * Public method calculates given equation in Infix Notation.
     * I use here functional interface Function.
     * @param infixEquation given equation in Infix Notation
     * @return result of the equation or ERROR_MESSAGE if equation is incorrect.
     */
    public String calculateInfixEquation(String infixEquation) {
        equation.clearFields();
        equation.setInfixEquation(infixEquation);
        Function<Double, String> doubleToString = String::valueOf;

        String postfixEquation = convertToPostfix(infixEquation);
        equation.setPostfixEquation(postfixEquation);

        if (!postfixEquation.equals(ERROR_MESSAGE)) {
            double result = calculatePostfixEquation(postfixEquation);
            equation.setResult(result);
            saveEquationToFile();
            return doubleToString.apply(result);
        } else
            return ERROR_MESSAGE;
    }

    /**
     * Method converts infixEquation from Infix Notation to Postfix Notation.
     *
     * @param infixEquation given infixEquation in Infix Notation
     * @return infixEquation in Postfix Notation
     */
    private String convertToPostfix(String infixEquation) {
        onpBuilder.delete(0, onpBuilder.length());
        boolean isEquationCorrect = isTheEquationCorrect(infixEquation);
        char currentChar = '0';
        char nextChar = '0';
        boolean isLastChar = false;

        if (isEquationCorrect) {
            for (int i = 0; i < infixEquation.length(); i++) {
                currentChar = infixEquation.charAt(i);
                if (i != infixEquation.length() - 1)
                    nextChar = infixEquation.charAt(i + 1);
                else
                    isLastChar = true;

                if (isDigit(currentChar) || isDot(currentChar)) {
                    onpBuilder.append(currentChar);
                    if (isLastChar || !isDigit(nextChar) && !isDot(nextChar))
                        onpBuilder.append(" ");
                } else
                    switch (currentChar) {

                        case (Operators.LEFT_PARENTHESIS): {
                            operatorStack.push(new LeftParenthesis());
                            break;
                        }

                        case (Operators.ADDITION): {
                            pushOperatorToStack(new Addition());
                            break;
                        }

                        case (Operators.SUBTRACTION): {
                            pushOperatorToStack(new Subtraction());
                            break;
                        }

                        case (Operators.RIGHT_PARENTHESIS): {
                            while (operatorStack.getSize() > 0 && operatorStack.peek().isNotALeftParenthesis()) {
                                appendCharFromStack();
                            }
                            operatorStack.pop();    // pop '(' sign
                            break;
                        }

                        case (Operators.MULTIPLICATION): {
                            pushOperatorToStack(new Multiplication());
                            break;
                        }

                        case (Operators.DIVISION): {
                            pushOperatorToStack(new Division());
                            break;
                        }

                        case (Operators.MODULO): {
                            pushOperatorToStack(new Modulo());
                            break;
                        }

                        case (Operators.ROOT_EXTRACTION): {
                            pushOperatorToStack(new RootExtraction());
                            break;
                        }

                        case (Operators.EXPONENTIATION): {
                            pushOperatorToStack(new Exponentiation());
                            break;
                        }

                        case (Operators.FACTORIAL): {
                            pushOperatorToStack(new Factorial());
                            break;
                        }
                    }
            }
        } else {
            return ERROR_MESSAGE;
        }

        appendRemainingOperatorsFromStack();
        return onpBuilder.toString();
    }

    /**
     * Method checks if the given infixEquation is correct on a few ways.
     *
     * @param infixEquation given equation to check
     * @return true if is correct, false otherwise.
     */
    public boolean isTheEquationCorrect(String infixEquation) {
        boolean isCorrect = true;
        int numberOfLeftParenthesis = 0;
        int numberOfRightParenthesis = 0;

        // empty string
        if (infixEquation.equals("")) {
            return false;
        }

        // +7, *4 error
        if (!isDigit(infixEquation.charAt(0)) && infixEquation.charAt(0) != '('){
            return false;
        }

        for (int i = 0; i < infixEquation.length() - 1; i++) {
            char currentChar = infixEquation.charAt(i);
            char nextChar = infixEquation.charAt(i + 1);

            // checking if infixEquation doesn't have two operators side by side, e.g. **, //
            if (!isDigit(currentChar) && currentChar == nextChar) {
                isCorrect = false;
                break;
            }

            // checking if infixEquation doesn't have two operators side by side, e.g. *!, /*
            if (!isDigit(currentChar) && !isDigit(nextChar)) {
                if (nextChar != '(') {
                    if (currentChar != ')') {
                        isCorrect = false;
                        break;
                    }
                }
            }
        }

        // checking if first character is not a digit, as well as '(' and '-'
        char firstChar = infixEquation.charAt(0);
        if (!isDigit(firstChar)) {
            isCorrect = firstChar == '(' || firstChar == '-';
        }

        //checking if last character is not a digit, as well as ')' and '!'
        char lastChar = infixEquation.charAt(infixEquation.length() - 1);
        if (!isDigit(lastChar)) {
            isCorrect = lastChar == ')' || lastChar == '!';
        }

        // checking if there is proper number of parentheses
        for (int i = 0; i < infixEquation.length(); i++) {
            if (infixEquation.charAt(i) == '(')
                ++numberOfLeftParenthesis;
            if (infixEquation.charAt(i) == ')')
                ++numberOfRightParenthesis;
        }

        if (numberOfLeftParenthesis != numberOfRightParenthesis)
            isCorrect = false;

        return isCorrect;
    }

    /**
     * Method calculates given equation in Postfix Notation.
     *
     * @param postfixEquation given equation in Postfix Notation
     * @return result of the equation
     */
    private double calculatePostfixEquation(String postfixEquation) {
        double result = 0.0;
        Double a;
        Double b;
        StringBuilder tmp = new StringBuilder();
        char currentChar;
        char nextChar;

        for (int i = 0; i < postfixEquation.length(); i++) {
            currentChar = postfixEquation.charAt(i);

            if (i != postfixEquation.length() - 1)
                nextChar = postfixEquation.charAt(i + 1);
            else
                return Double.parseDouble(numberStack.pop());

            if (isDigit(currentChar)) {
                tmp.append(currentChar);
                if (!isDigit(nextChar) && !isDot(nextChar)) {
                    numberStack.push(tmp.toString());
                    tmp.delete(0, tmp.length());
                }
            } else if (currentChar == Operators.DOT) {
                tmp.append(Operators.DOT);
            } else if (currentChar == Operators.FACTORIAL) {
                a = Double.parseDouble(numberStack.pop());
                long tmpRes = factorial((int) a.doubleValue());
                numberStack.push(String.valueOf(tmpRes));
            } else if (currentChar != ' ') {
                b = Double.parseDouble(numberStack.pop());
                a = Double.parseDouble(numberStack.pop());

                switch (currentChar) {
                    case (Operators.ADDITION): {
                        numberStack.push((a + b) + "");
                        break;
                    }
                    case (Operators.SUBTRACTION): {
                        numberStack.push((a - b) + "");
                        break;
                    }
                    case (Operators.MULTIPLICATION): {
                        numberStack.push((a * b) + "");
                        break;
                    }
                    case (Operators.DIVISION): {
                        numberStack.push((a / b) + "");
                        break;
                    }
                    case (Operators.MODULO): {
                        numberStack.push((a % b) + "");
                        break;
                    }
                    case (Operators.ROOT_EXTRACTION): {
                        numberStack.push((Math.pow(b, 1 / a)) + "");
                        break;
                    }
                    case (Operators.EXPONENTIATION): {
                        numberStack.push((Math.pow(a, b)) + "");
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method appends char from stack to postfix equation with space.
     */
    private void appendCharFromStack() {
        onpBuilder.append(operatorStack.pop()).append(" ");
    }

    /**
     * Method appends remaining operators from stack to postfix equation.
     */
    private void appendRemainingOperatorsFromStack() {
        while (!operatorStack.empty()) {
            appendCharFromStack();
        }
    }

    /**
     * Method saves Equation to file. It consists infix, postfix equations and result of the operation.
     */
    public void saveEquationToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Equation.fileName, true));
            out.writeObject(equation);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method calculates factorial of given number
     *
     * @param number given number to calculate
     * @return result of the factorial operation
     */
    private long factorial(int number) {
        long result = 1;

        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }

    /**
     * Method pushes proper operator to the top of the stack
     * As we can see here, this method actively use polymorphism
     *
     * @param operator given Operator, in fact a class extending Operator class.
     */
    private void pushOperatorToStack(Operator operator) {
        while (operatorStack.getSize() > 0 && operator.isLowerOrEqualPriority(operatorStack.peek())) {
            appendCharFromStack();
        }
        operatorStack.push(operator);
    }

    /**
     * Method checks if param c is digit or not.
     *
     * @param c char to check
     * @return true if param is digit, false otherwise
     */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Method checks if param c is dot or not.
     *
     * @param c char to check
     * @return true if param is dot, false otherwise
     */
    private boolean isDot(char c) {
        return c == '.';
    }
}
