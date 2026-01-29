import java.util.ArrayList;

/**
 * Exercise 19.3: Distinct elements in ArrayList
 * 
 * Write a generic method that returns a new ArrayList.
 * The new list contains the nonduplicate elements from the original list.
 * 
 * @author Evan Yango
 */
public class Exercise19_03 {
    
    /**
     * Removes duplicate elements from a list.
     * 
     * TODO: Complete this method.
     * Hint: Create a new ArrayList, iterate through the original list,
     * and add elements that are not already in the new list.
     * Use the contains() method to check for duplicates.
     * 
     * @param list the original list (may contain duplicates)
     * @return a new list with only unique elements
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // TODO: Implement this method
        ArrayList<E> unique = new ArrayList<>();
        for (E item : list) {
            if (!unique.contains(item)) {
                unique.add(item);
            }
        }
        return unique;
    }

    public static void main(String[] args) {
        // Test with Integers
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(2);
        intList.add(3);
        intList.add(1);
        intList.add(4);
        
        System.out.println("Original list: " + intList);
        ArrayList<Integer> distinctInts = removeDuplicates(intList);
        System.out.println("Distinct elements: " + distinctInts);
        
        // Test with Strings
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("cherry");
        stringList.add("banana");
        
        System.out.println("\nOriginal list: " + stringList);
        ArrayList<String> distinctStrings = removeDuplicates(stringList);
        System.out.println("Distinct elements: " + distinctStrings);
    }
}
