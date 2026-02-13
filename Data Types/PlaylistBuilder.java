import java.io.*;
import java.util.*;

/**
 * PlaylistBuilder
 * 
 * Reads a playlist file, counts song frequencies, and builds a new playlist
 * sorted by listening frequency (ascending or descending based on user choice).
 * 
 * Demonstrates: Maps for counting, sorting by value, file I/O, user interaction
 * 
 * TODO: Fill in the main method to complete this program.
 * 
 * @author Evan Yango
 */
public class PlaylistBuilder {

    public static void main(String[] args) {
        // TODO 1: Create a HashMap to store song frequencies
        // Key: song title (String)
        // Value: play count (Integer)
        // ============================================
        Map<String, Integer> songCount = new HashMap<>();
        // ============================================

        // TODO 2: Read the playlist file and count song frequencies
        // Use try-catch for FileNotFoundException
        // Use Scanner to read song titles (one per line)
        // The filename is "sample_playlist.txt"
        // ============================================
        try (Scanner scanner = new Scanner(new File("sample_playlist.txt"))) {
            while (scanner.hasNextLine()) {
                String song = scanner.nextLine().trim();
                
                // TODO 2a: Skip empty lines
                if (song.isEmpty()) {
                    continue;
                }
                
                // TODO 2b: Update the song count in the map
                // If song exists: increment count
                // If song is new: set count to 1
                if (songCount.containsKey(song)) {
                    songCount.put(song, songCount.get(song) + 1);
                } else {
                    songCount.put(song, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File 'sample_playlist.txt' not found!");
            return;
        }

        // TODO 3: Display analysis
        // Show total unique songs and top 5 most played
        // ============================================
        System.out.println("=== Playlist Builder ===\n");
        System.out.println("Song frequency analysis complete!");
        System.out.println("Found " + songCount.size() + " unique songs.\n");
        
        // Show top 5 most played songs
        System.out.println("Top 5 most played:");
        
        // TODO 3a: Create a sorted list of songs to display top 5
        // Create ArrayList from songCount.entrySet()
        // Sort using Collections.sort() with a Comparator
        // (Sort by value descending: e2.getValue().compareTo(e1.getValue()))
        List<Map.Entry<String, Integer>> topSongs = new ArrayList<>(songCount.entrySet());
        Collections.sort(topSongs, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Show first 5
        for (int i = 0; i < Math.min(5, topSongs.size()); i++) {
            Map.Entry<String, Integer> entry = topSongs.get(i);
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " plays)");
        }
        System.out.println();
        // ============================================

        // TODO 4: Menu - ask user for playlist type
        // Display two options:
        // 1) Least Played First (discovery mode)
        // 2) Most Played First (favorites mode)
        // Read user input with Scanner
        // ============================================
        Scanner userInput = new Scanner(System.in);
        System.out.println("How would you like to build your playlist?");
        System.out.println("1) Least Played First (discover new songs)");
        System.out.println("2) Most Played First (hear your favorites)");
        System.out.print("\nEnter choice (1 or 2): ");
        
        int choice = userInput.nextInt();
        userInput.close();
        // ============================================

        // TODO 5: Validate user input
        // If choice is not 1 or 2, display error and exit
        // ============================================
        if (choice != 1 && choice != 2) {
            System.out.println("\nInvalid choice! Please enter 1 or 2.");
            return;
        }
        // ============================================

        // TODO 6: Sort the songs by frequency
        // Convert HashMap to a sorted List of entries
        // If choice == 1 (least played): sort ascending (smallest count first)
        // If choice == 2 (most played): sort descending (largest count first)
        // Hint: Use .stream().sorted() with a Comparator
        // ============================================
        List<Map.Entry<String, Integer>> sortedSongs = topSongs;
        if (choice == 1) Collections.reverse(sortedSongs);
        // ============================================

        // TODO 7: Generate output filename and message based on choice
        // If choice == 1: filename = "playlist_least_played.txt"
        // If choice == 2: filename = "playlist_most_played.txt"
        // ============================================
        String filename = choice == 1 ? "playlist_least_played.txt" : "playlist_most_played.txt";
        // ============================================

        // TODO 8: Write the sorted playlist to a file
        // Use PrintWriter to write one song per line
        // Include try-catch for IOException
        // ============================================
        System.out.println("\nCreating " + filename + "...");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : sortedSongs) {
                writer.println(entry.getKey());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }
        // ============================================

        // TODO 9: Display completion message
        // Show the number of songs in the playlist
        // ============================================
        System.out.println("Done! Your playlist has " + sortedSongs.size() + " songs.");
        // ============================================
    }
}
