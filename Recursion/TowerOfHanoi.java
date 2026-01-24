import java.util.ArrayList;
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
public class TowerOfHanoi {
    
    // Part 3: Move counter (you'll add this)
    private static int moveCount = 0;

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
        System.out.println("Move disk " + disk + " from " + source + " to " + destination);
        moveCount++;
        displayTowers();
    }
    
    /**
     * PART 1: Implement the classic Tower of Hanoi solver
     * 
     * TODO: Implement this recursive method
     * 
     * Base case: if n == 1, move disk from source to destination
     * Recursive case:
     *   1. Move n-1 disks from source to auxiliary (using destination)
     *   2. Move disk n from source to destination
     *   3. Move n-1 disks from auxiliary to destination (using source)
     * 
     * @param n number of disks to move
     * @param source the source peg (e.g., 'A')
     * @param destination the destination peg (e.g., 'C')
     * @param auxiliary the auxiliary peg (e.g., 'B')
     */
    public static void moveDisks(int n, char source, char destination, char auxiliary) {
        // TODO: Implement base case
        if (n == 1) moveDisk(source, destination);
        
        // TODO: Implement recursive case (3 steps)
        else {
            moveDisks(n - 1, source, auxiliary, destination);
            moveDisk(source, destination);
            moveDisks(n - 1, auxiliary, destination, source);
        }
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
    public static void displayTowers() {
        // TODO: Implement tower visualization
        // System.out.println("--- Tower State ---");
        // Display pegs A, B, C and their disks
        System.out.println("A: " + pegA.toString());
        System.out.println("B: " + pegB.toString());
        System.out.println("C: " + pegC.toString());
        System.out.println();
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
        int n = 3; // Start with 3 disks
        
        System.out.println("Tower of Hanoi - " + n + " disks");
        initializePegs(n);

        System.out.println("Moving disks from A to C using B\n");
        
        // Reset move counter
        moveCount = 0;
        
        // Solve the puzzle
        moveDisks(n, 'A', 'C', 'B');
        
        // Display statistics
        printStatistics(n);
        
        // Test with different numbers of disks
        n = 4;
        initializePegs(n);
        System.out.println("\n\n=== Try with 4 disks ===");
        moveCount = 0;
        moveDisks(n, 'A', 'C', 'B');
        printStatistics(n);
    }
}
