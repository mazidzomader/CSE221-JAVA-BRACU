import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Count_the_Inversion {
    static class SortResult {
        long inversionCount;
        int[] sortedArray;
        
        SortResult(long inversionCount, int[] sortedArray) {
            this.inversionCount = inversionCount;
            this.sortedArray = sortedArray;
        }
    }
    
    public static SortResult mergeArrays(int[] firstArray, int[] secondArray) {
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int[] mergedArray = new int[firstLength + secondLength];
        long inversions = 0;
        int firstIndex = 0, secondIndex = 0, mergeIndex = 0;
        
        while (firstIndex < firstLength && secondIndex < secondLength) {
            if (firstArray[firstIndex] <= secondArray[secondIndex]) {
                mergedArray[mergeIndex++] = firstArray[firstIndex++];
            } else {
                mergedArray[mergeIndex++] = secondArray[secondIndex++];
                inversions += (firstLength - firstIndex);
            }
        }
        
        while (firstIndex < firstLength) {
            mergedArray[mergeIndex++] = firstArray[firstIndex++];
        }
        
        while (secondIndex < secondLength) {
            mergedArray[mergeIndex++] = secondArray[secondIndex++];
        }
        
        return new SortResult(inversions, mergedArray);
    }
    
    public static SortResult performMergeSort(int[] inputArray) {
        if (inputArray.length <= 1) {
            return new SortResult(0, inputArray);
        }
        
        int middle = inputArray.length / 2;
        int[] leftHalf = new int[middle];
        int[] rightHalf = new int[inputArray.length - middle];
        
        // Split array
        for (int i = 0; i < middle; i++) {
            leftHalf[i] = inputArray[i];
        }
        for (int i = middle; i < inputArray.length; i++) {
            rightHalf[i - middle] = inputArray[i];
        }
        
        SortResult leftResult = performMergeSort(leftHalf);
        SortResult rightResult = performMergeSort(rightHalf);
        SortResult mergeResult = mergeArrays(leftResult.sortedArray, rightResult.sortedArray);
        
        return new SortResult(
            mergeResult.inversionCount + leftResult.inversionCount + rightResult.inversionCount,
            mergeResult.sortedArray
        );
    }
    
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
        
        SortResult finalResult = performMergeSort(numbers);
        
        // Print inversion count
        System.out.println(finalResult.inversionCount);
        
        // Print sorted array
        for (int i = 0; i < finalResult.sortedArray.length; i++) {
            System.out.print(finalResult.sortedArray[i]);
            if (i < finalResult.sortedArray.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}