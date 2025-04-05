
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Ordering_Binary_Tree {
    // Function to perform binary tree ordering
    public static ArrayList<Integer> orderBinaryTree(int[] array, int leftIndex, int rightIndex, 
                                                    ArrayList<Integer> resultList) {
        if (leftIndex > rightIndex) {
            return resultList;
        }
        
        int middle = (leftIndex + rightIndex) / 2;
        resultList.add(array[middle]);
        
        // Left subtree
        orderBinaryTree(array, leftIndex, middle - 1, resultList);
        // Right subtree
        orderBinaryTree(array, middle + 1, rightIndex, resultList);
        
        return resultList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read array size
        int arraySize = Integer.parseInt(reader.readLine().trim());
        
        // Read array elements
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] inputArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            inputArray[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // Process the array and get result
        ArrayList<Integer> result = orderBinaryTree(inputArray, 0, arraySize - 1, 
                                                  new ArrayList<Integer>());
        
        // Print results
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}