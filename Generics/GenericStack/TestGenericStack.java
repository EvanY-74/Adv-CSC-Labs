import java.util.ArrayList;

public class TestGenericStack {
    public static void main(String[] args) {
        // Test with Strings
        GenericStack<String> stringStack = new GenericStack<>();
        stringStack.push("The");
        stringStack.push("quick");
        stringStack.push("brown");
        stringStack.push("fox");
        
        System.out.println("String Stack: " + stringStack);
        System.out.println("Peek: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Size: " + stringStack.getSize());
        
        // Test with Integers
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        intStack.push(40);
        intStack.push(50);
        
        System.out.println("\nInteger Stack: " + intStack);
        System.out.println("Peek: " + intStack.peek());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("Size: " + intStack.getSize());
    }
}
