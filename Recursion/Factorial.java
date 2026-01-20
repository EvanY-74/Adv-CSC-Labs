import java.math.BigInteger;
import java.util.Scanner;

/**
 * Exercise 18.1: Compute factorial using recursion with BigInteger
 * 
 * Using the BigInteger class, you can find the factorial for a large number.
 * Implement the factorial method using recursion.
 * Prompt the user to enter an integer and display its factorial.
 * 
 * Example: 100! is a huge number with 158 digits!
 * 
 * @author Evan Yango
 */
public class Factorial {
    /**
     * Recursive factorial method using BigInteger
     * 
     * TODO: Implement this method
     * 
     * Base case: if n equals 0, return 1
     * Recursive case: return n * factorial(n - 1)
     * 
     * Hint: Use BigInteger methods:
     * - n.equals(BigInteger.ZERO) to check if n is 0
     * - n.multiply(other) to multiply
     * - n.subtract(BigInteger.ONE) to subtract 1
     * 
     * @param n the number to compute factorial for
     * @return n! as a BigInteger
     */
    public static BigInteger factorial(BigInteger n) {
        // TODO: Implement base case
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) return BigInteger.ONE;
        
        // TODO: Implement recursive case
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a non-negative integer: ");
        int number = input.nextInt();
        
        // Convert to BigInteger
        BigInteger n = new BigInteger(String.valueOf(number));
        
        // Calculate factorial
        BigInteger result = factorial(n);
        
        // Display result
        System.out.println(number + "! = " + result);
        
        input.close();
    }
}