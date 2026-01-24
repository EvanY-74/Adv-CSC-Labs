import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

/**
 * Tower of Hanoi Lab
 * 
 * The Tower of Hanoi is a classic problem that demonstrates recursion.
 * 
 * Rules:
 * - You have 3 pegs (A, B, C) and n disks of different sizes
 * - All disks start on peg A, sorted by size (largest at bottom)
 * - Goal: Move all disks from peg A to peg C
 * - Only one disk can be moved at a time
 * - A larger disk can never be placed on top of a smaller disk
 * 
 * Recursive solution:
 * To move n disks from source to destination using auxiliary peg:
 *   1. Move n-1 disks from source to auxiliary (using destination as helper)
 *   2. Move the largest disk from source to destination
 *   3. Move n-1 disks from auxiliary to destination (using source as helper)
 * 
 * @author Evan Yango
 */
public class PlayableTowerOfHanoi {

    
    // Part 3: Move counter (you'll add this)
    private static int moveCount = 0;
    static Scanner scanner = new Scanner(System.in);

    /**
     * Convert from char disk to reference to peg
     * 
     * @param disk char of peg
     * @return reference to peg
     */
    public static ArrayList getPeg(char peg) {
        switch (peg) {
            case 'A':
                return pegA;
            case 'B':
                return pegB;
            case 'C':
                return pegC;
            default:
                throw new Error("Invalid peg");
        }
    }

    /**
     * Move one disk to another peg
     * 
     * @param source source peg
     * @param destination destination peg
     */
    public static void moveDisk(char source, char destination) {
        ArrayList sourcePeg = getPeg(source);
        ArrayList destinationPeg = getPeg(destination);
        int disk = (int) sourcePeg.remove(sourcePeg.size() - 1);
        destinationPeg.add(disk);
        // System.out.println("Move disk " + disk + " from " + source + " to " + destination);
        moveCount++;
    }
    
    /**
     * Get user input process it
     * 
     * @param input either "source" or "destination"
     * @param destination destination peg
     */
    public static char halfMove(String input) {
        System.out.print(input + ": ");
        char move = scanner.next().charAt(0);
        if (move == '1') return 'A';
        else if (move == '2') return 'B';
        else if (move == '3') return'C';
        else if (move != 'A' && move != 'B' && move != 'C') return '!';
        return move;
    }
    
    /**
     * Get user input and move based upon it
     * 
     * @param source source peg
     * @param destination destination peg
     */
    public static void move() {
        // get source move
        char source = halfMove("source");
        if (source == '!') {
            System.out.println("Invalid source move");
            return;
        }
        
        // get destination move
        char destination = halfMove("destination");
        if (destination == '!') {
            System.out.println("Invalid destination move");
            return;
        }
        
        // check if valid
        if (!isValidMove(source, destination)) {
            System.out.println("This move cannot be preformed");
            return;
        }
        
        // move
        moveDisk(source, destination);
    }
    
    /** Check if player move is valid
     *
     * @return valid or invalid?
     */
     public static boolean isValidMove(char source, char destination) {
        if (source == destination) return false;
        ArrayList sourcePeg = getPeg(source);
        if (sourcePeg.size() == 0) return false;
        ArrayList destinationPeg = getPeg(destination);
        if (destinationPeg.size() == 0) return true;
        return (int) sourcePeg.get(sourcePeg.size() - 1) < (int) destinationPeg.get(destinationPeg.size() - 1);
     }
    
    /**
     * PART 2: Add visualization
     * 
     * Modify this method to display the state of the towers after each move.
     * 
     * You can represent the towers however you like. Example:
     * A: [3, 2, 1]
     * B: []
     * C: []
     * 
     * Or get creative with ASCII art!
     * 
     * Hint: You'll need to track which disks are on which peg.
     * Consider using ArrayList<Integer> for each peg.
     */
    public static void displayTowers(int n) {
        // top
        String spacing = " ".repeat(n);
        System.out.println(spacing + "|" + spacing.repeat(2) + " |" + spacing.repeat(2) + " |");
        for (int i = 0; i < n; i++) {
            int size = n - i - 1 < pegA.size() ? pegA.get(n - i - 1) : 0;
            // System.out.println(size);
            spacing = " ".repeat(n - size);
            char middle = size == 0 ? '|' : '=';
            System.out.print(spacing + "=".repeat(size) + middle + "=".repeat(size) + spacing + " ");
            
            size = n - i - 1 < pegB.size() ? pegB.get(n - i - 1) : 0;
            // System.out.println(size);
            spacing = " ".repeat(n - size);
            middle = size == 0 ? '|' : '=';
            System.out.print(spacing + "=".repeat(size) + middle + "=".repeat(size) + spacing + " ");
            
            size = n - i - 1 < pegC.size() ? pegC.get(n - i - 1) : 0;
            // System.out.println(size);
            spacing = " ".repeat(n - size);
            middle = size == 0 ? '|' : '=';
            System.out.print(spacing + "=".repeat(size) + middle + "=".repeat(size) + spacing);
            System.out.print('\n');
        }
        System.out.println("-".repeat(6 * n + 5));
    }
    
    /**
     * PART 3: Add move counting and validation
     * 
     * Enhance your solution to:
     * 1. Count total moves
     * 2. Verify the solution uses the minimum number of moves (2^n - 1)
     * 3. Optional: Add validation to ensure no illegal moves
     */
    public static void printStatistics(int n) {
        // TODO: Print statistics
        System.out.println("\n=== Statistics ===");
        System.out.println("Number of disks: " + n);
        System.out.println("Total moves: " + moveCount);
        System.out.println("Minimum possible moves: " + ((int)Math.pow(2, n) - 1));
        
        // Verify correctness
        if (moveCount == (int)Math.pow(2, n) - 1) {
            System.out.println("SUCCESS! Optimal solution.");
        } else {
            System.out.println("WARNING: Not optimal.");
        }
    }
    
    public static boolean checkSolved() {
        return pegA.size() == 0 && pegB.size() == 0;
    }

    static ArrayList<Integer> pegA = new ArrayList<>();
    static ArrayList<Integer> pegB = new ArrayList<>();
    static ArrayList<Integer> pegC = new ArrayList<>();

    public static void initializePegs(int n) {
        pegA = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            pegA.add(i);
        }
        pegB = new ArrayList<>();
        pegC = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        System.out.print("Enter number of disks: ");
        int n = scanner.nextInt();
        
        System.out.println("Tower of Hanoi - " + n + " disks");
        initializePegs(n);
        
        // Set move counter
        moveCount = 0;
        
        while (true) {
            displayTowers(n);
            move();
            if (checkSolved()) break;
            // moveDisk(n, 'A', 'C', 'B');
        }
        
        // Display statistics
        displayTowers(n);
        printStatistics(n);

    }
}
