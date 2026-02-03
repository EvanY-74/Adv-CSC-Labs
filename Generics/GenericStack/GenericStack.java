import java.util.ArrayList;

/**
 * Lab: GenericStack
 * 
 * A generic stack implementation using ArrayList.
 * Students will implement all methods.
 * 
 * @author Evan Yango
 */
public class GenericStack<E> {
    
    private ArrayList<E> list = new ArrayList<>();
    
    /**
     * TODO: Implement this method.
     * Hint: Add the element to the end of the list (top of stack).
     */
    public void push(E element) {
        this.list.add(element);
    }
    
    /**
     * TODO: Implement this method.
     * Hint: Remove and return the last element (top of stack).
     * Return type is E.
     */
    public E pop() {
        return this.list.remove(list.size() - 1);
    }
    
    /**
     * TODO: Implement this method.
     * Hint: Return the last element without removing it.
     * Return type is E.
     */
    public E peek() {
        return this.list.get(this.list.size() - 1);
    }
    
    /**
     * TODO: Implement this method.
     * Hint: Return the number of elements in the stack.
     */
    public int getSize() {
        return this.list.size();
    }
    
    /**
     * TODO: Implement this method.
     * Hint: Return true if the stack is empty.
     */
    public boolean isEmpty() {
        System.out.println("TODO: isEmpty() not implemented yet");
        return this.list.size() == 0;
    }
    
    @Override
    public String toString() {
        return "Stack: " + this.list.toString();
    }
}
