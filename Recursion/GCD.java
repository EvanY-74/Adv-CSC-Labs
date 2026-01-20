import java.util.Scanner;

/**
 * Exercise 18.2: Compute greatest common divisor using recursion
 * 
 * The gcd(m, n) can be defined recursively as follows:
 * - If m % n is 0, gcd(m, n) is n
 * - Otherwise, gcd(m, n) is gcd(n, m % n)
 * 
 * This is the Euclidean algorithm.
 * 
 * Write a recursive method to find the GCD.
 * Prompt the user to enter two integers and display their GCD.
 * 
 * @author Evan Yango
 */
public class GCD {
    /**
     * Recursive GCD method using Euclidean algorithm
     * 
     * TODO: Implement this method
     * 
     * Base case: if n is 0, return m
     * Recursive case: return gcd(n, m % n)
     * 
     * @param m first number
     * @param n second number
     * @return greatest common divisor of m and n
     */
    public static int gcd(int m, int n) {
        // TODO: Implement base case
        if (m % n == 0) return n;
        
        // TODO: Implement recursive case
        return gcd(n, m % n);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter first integer: ");
        int m = input.nextInt();
        
        System.out.print("Enter second integer: ");
        int n = input.nextInt();
        
        // Calculate GCD
        int result = gcd(m, n);
        
        // Display result
        System.out.println("The greatest common divisor of " + m + 
                           " and " + n + " is " + result);
        
        input.close();
    }
}