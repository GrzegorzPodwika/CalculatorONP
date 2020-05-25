package calculatorONP;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Podwika
 * Class implementing stack via LinkedList
 */
public class ListStack<T> {
    private final List<T> stack = new LinkedList<>();
    private final int capacity = 50;

    /**
     * Method pops a value from top of the stack
     * @return pop value
     */
    public T pop(){
        if (stack.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty!");
        }

        T result = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return result;
    }

    /**
     * Method pushes a value to top of the stack
     */
    public void push(T object){
        if (stack.size() >= 20){
            throw new ArrayIndexOutOfBoundsException("Stack is full!");
        }

        stack.add(object);
    }

    /**
     * Method checks if this stack is empty
     * @return boolean - true if the stack is empty or false otherwise
     */
    public boolean empty(){
        return stack.isEmpty();
    }

    /**
     * Method looks at the object at the top of this stack
     * without removing it form the stack.
     * @return the object at the top of this stack
     */
    public T peek(){
        if (stack.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is full!");
        }

        return stack.get(stack.size() - 1);
    }

    /**
     * Method sets new size of this stack.
     * @param customSize new size of this stack
     */
    public void setSize(int customSize){
        while (stack.size() > customSize){
            stack.remove(stack.size() - 1);
        }
    }

    /**
     * Method gets size of this stack.
     * @return size of this stack
     */
    public int getSize(){
        return stack.size();
    }

    /**
     * Method returns an object from index given as the param
     * @param position index of looking object
     * @return an object from index given as the param
     */
    public T showValue(int position){
        if (position < stack.size() && position >= 0){
            return stack.get(position);
        }else {
            throw new IllegalArgumentException("Given position is outside of [0, stack.size()]");
        }
    }

    /**
     * Method returns text representation of the stack
     * @return text representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stos: \n");
        for (T s: stack){
            sb.append(s).append(' ');
        }
        return sb.toString();
    }
}
