/**
 * Exercise 19.5: Maximum element in an array
 * 
 * Implement the following method that returns the maximum element in an array:
 * 
 * public static <E extends Comparable<E>> E max(E[] list)
 * 
 * Write a test program that prompts the user to enter 10 integers,
 * invokes this method to find the max, and displays the maximum number.
 * 
 * @author Evan Yango
 */
public class Exercise19_05 {
    
    /**
     * Finds the maximum element in an array.
     * 
     * TODO: Complete this method.
     * Hint: Use a bounded generic type <E extends Comparable<E>>.
     * Start with the first element as max, then compare with others.
     * Use compareTo() to compare elements.
     * 
     * @param list the array to search
     * @return the maximum element
     */
    public static <E extends Comparable<E>> E max(E[] list) {
        E max = list[0];
        for (int i = 0; i < list.length; i++) {
            if (max.compareTo(list[i]) < 0) max = list[i];
        }
        return max;
    }

    public static void main(String[] args) {
        // Test with Integers - ask user for 10 integers
        Integer[] numbers = new Integer[10];
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.println("Enter 10 integers:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Integer " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        
        Integer maxNum = max(numbers);
        System.out.println("\nMaximum number: " + maxNum);
        
        scanner.close();
    }
}
