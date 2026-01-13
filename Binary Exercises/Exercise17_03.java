import java.io.*;

/**
 * Exercise 3: Track how many times a program has been executed
 *
 * Requirements:
 * - Store an execution count in a file named Exercise17_03.dat
 * - Each time this program runs, increment the count by 1
 * - Display the current count
 * - Use the file persistence pattern:
 *   1. Read existing count (if file exists)
 *   2. Increment the count
 *   3. Write updated count back to file
 *
 * @author Evan Yango
 */
public class Exercise17_03 {

    public static void main(String[] args) throws IOException {

        File file = new File("Exercise17_03.dat");
        int count = 0;

        // STEP 1: Read existing count if file exists
        // TODO: Check if file exists using file.exists()
        if (file.exists()) {
            // TODO: If file exists, open DataInputStream and read the count
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            count = dataInputStream.readInt();
            // TODO: Close the input stream (or use try-with-resources)
            dataInputStream.close();
        }

        // STEP 2: Increment the count
        // TODO: Add 1 to count

        count++;

        // STEP 3: Write updated count back to file
        // TODO: Create DataOutputStream for the file
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));

        // TODO: Write the updated count
        dataOutputStream.writeInt(count);

        // TODO: Close the output stream (or use try-with-resources)
        dataOutputStream.close();

        // Display the result
        System.out.println("This program has been executed " + count + " time(s).");
    }
}