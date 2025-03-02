import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Can_you_Iterate_the_Binary_String {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); 
        
        StringBuilder output = new StringBuilder(); 
        
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            output.append(bin_search(temp, 0, temp.length() - 1)).append("\n");
        }
        
        System.out.print(output); 
    }

    private static int bin_search(String str, int l, int r) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l) / 2;  

        if (str.charAt(mid) == '1') {
            int leftSearch = bin_search(str, l, mid - 1);
            return (leftSearch != -1) ? leftSearch : mid + 1;  
        }

        return bin_search(str, mid + 1, r);
    }
}

