import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class SevenKingsofKonigsberg {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        
        String[] firstEnd = reader.readLine().split(" ");
        String[] secondEnd = reader.readLine().split(" ");
        
        AdjacencyList adjacencyList = new AdjacencyList(n);
        for (int i = 0; i < m; i++) {
            int u = Integer.parseInt(firstEnd[i]);
            int v = Integer.parseInt(secondEnd[i]);
            adjacencyList.addEdge(u, v);
        }
        
        int[] degree = adjacencyList.degree;
        sevenKingsOfKonigsberg(degree);
    }

    static void sevenKingsOfKonigsberg(int[] arr) {
        boolean evenFlag = true;
        for (int i : arr) {
            if (i % 2 == 1) {
                evenFlag = false;
                break;
            }
        }
        int exactTwoOdd = 0;
        for (int i : arr) {
            if (i % 2 == 1) {
                exactTwoOdd++;
            }
        }
        if (evenFlag || exactTwoOdd == 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
class Node {
    int value;
    Node next;

    Node(int data) {
        this.value = data;
        this.next = null;
    }
}

class AdjacencyList {
    List<List<Integer>> head;
    int[] degree;

    AdjacencyList(int n) {
        head = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            head.add(new ArrayList<>());
        }
        degree = new int[n];
    }

    void addEdge(int u, int v) {
        head.get(u - 1).add(v);
        head.get(v - 1).add(u);
        degree[u - 1]++;
        degree[v - 1]++;
    }
}