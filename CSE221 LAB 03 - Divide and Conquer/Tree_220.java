import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Tree_220 {
    // Function to find index of element in array
    private static int findIndex(int[] array, int element, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    // Function to perform postorder traversal
    public static ArrayList<Integer> postorderTraversal(int[] inorder, int[] preorder, 
                                                       int inStart, int inEnd, 
                                                       int preStart, int preEnd) {
        ArrayList<Integer> result = new ArrayList<>();
        
        // Base case: empty arrays
        if (inStart > inEnd || preStart > preEnd) {
            return result;
        }

        // Get root from preorder (first element)
        int root = preorder[preStart];
        
        // Find root index in inorder
        int rootIndex = findIndex(inorder, root, inStart, inEnd);
        
        // Calculate sizes of left and right subtrees
        int leftSubtreeSize = rootIndex - inStart;
        
        // Recursively process left subtree
        ArrayList<Integer> left = postorderTraversal(inorder, preorder,
            inStart, rootIndex - 1,
            preStart + 1, preStart + leftSubtreeSize);
            
        // Recursively process right subtree
        ArrayList<Integer> right = postorderTraversal(inorder, preorder,
            rootIndex + 1, inEnd,
            preStart + leftSubtreeSize + 1, preEnd);

        // Combine results: left -> right -> root
        result.addAll(left);
        result.addAll(right);
        result.add(root);
        
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read array size
        int size = Integer.parseInt(reader.readLine().trim());
        
        // Read inorder array
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] inorder = new int[size];
        for (int i = 0; i < size; i++) {
            inorder[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // Read preorder array
        tokenizer = new StringTokenizer(reader.readLine());
        int[] preorder = new int[size];
        for (int i = 0; i < size; i++) {
            preorder[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // Get postorder traversal
        ArrayList<Integer> result = postorderTraversal(inorder, preorder, 
                                                     0, size - 1, 
                                                     0, size - 1);
        
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