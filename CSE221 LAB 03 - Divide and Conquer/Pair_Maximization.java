import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pair_Maximization {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read array size
        int arraySize = Integer.parseInt(reader.readLine().trim());
        
        // Read input array
        int[] numbers = new int[arraySize];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine().trim());
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // Initialize variables
        long maxResult = Long.MIN_VALUE;  // Using long to handle large values
        long maxBeforeCurrent = numbers[0];
        
        // Process array
        for (int currentIndex = 1; currentIndex < arraySize; currentIndex++) {
            // Calculate current element squared
            long currentSquared = (long)numbers[currentIndex] * numbers[currentIndex];
            // Update maximum result
            maxResult = Math.max(maxResult, maxBeforeCurrent + currentSquared);
            // Update maximum value seen before current position
            maxBeforeCurrent = Math.max(maxBeforeCurrent, numbers[currentIndex]);
        }
        
        System.out.println(maxResult);
    }
}