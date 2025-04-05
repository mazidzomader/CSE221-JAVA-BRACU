import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_Beautiful_Sorted_List {
    public static void main(String[] args) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
 
        int size1 = Integer.parseInt(inputReader.readLine());
        int[] array1 = new int[size1];
        String[] input1 = inputReader.readLine().split(" ");
        for (int i = 0; i < size1; i++) {
            array1[i] = Integer.parseInt(input1[i]);
        }
 
        int size2 = Integer.parseInt(inputReader.readLine());
        int[] array2 = new int[size2];
        String[] input2 = inputReader.readLine().split(" ");
        for (int i = 0; i < size2; i++) {
            array2[i] = Integer.parseInt(input2[i]);
        }
 
        int index1 = 0, index2 = 0, mergedIndex = 0;
        int[] mergedArray = new int[size1 + size2];
 
        while (index1 < size1 && index2 < size2) {
            if (array1[index1] < array2[index2]) {
                mergedArray[mergedIndex] = array1[index1];
                index1++;
            } else {
                mergedArray[mergedIndex] = array2[index2];
                index2++;
            }
            mergedIndex++;
        }
 
        while (index1 < size1) {
            mergedArray[mergedIndex] = array1[index1];
            index1++;
            mergedIndex++;
        }
 
        while (index2 < size2) {
            mergedArray[mergedIndex] = array2[index2];
            index2++;
            mergedIndex++;
        }

        for (int i = 0; i < mergedArray.length; i++) {
            output.append(mergedArray[i]).append(" ");
        }
 
        System.out.println(output.toString().trim());
    }
}
