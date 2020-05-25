package operators;

public abstract class Operator {
    protected int priority;
    protected char sign;

    public boolean isLowerOrEqualPriority(Operator op){
        return this.priority <= op.priority;
    }

    public boolean isNotALeftParenthesis(){
        return this.sign != '(';
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }
}
