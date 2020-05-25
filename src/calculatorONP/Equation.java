package calculatorONP;

import java.io.Serializable;

public class Equation implements Serializable {

    public static final String fileName = ".\\equation.dt";
    private String infixEquation;
    private String postfixEquation;
    private double result;

    public String getInfixEquation() {
        return infixEquation;
    }

    public void setInfixEquation(String infixEquation) {
        this.infixEquation = infixEquation;
    }

    public String getPostfixEquation() {
        return postfixEquation;
    }

    public void setPostfixEquation(String postfixEquation) {
        this.postfixEquation = postfixEquation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Equation() {
        this.infixEquation = "";
        this.postfixEquation = "";
        this.result = 0.0;
    }

    public Equation(String infixEquation, String postfixEquation, Double result) {
        this.infixEquation = infixEquation;
        this.postfixEquation = postfixEquation;
        this.result = result;
    }

    public void clearFields(){
        this.infixEquation = "";
        this.postfixEquation = "";
        this.result = 0.0;
    }
}
