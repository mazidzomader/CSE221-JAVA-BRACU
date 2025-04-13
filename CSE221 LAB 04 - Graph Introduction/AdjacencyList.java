import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdjacencyList {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        List adjacencyList = new List(n);
        String[] xLine = reader.readLine().split(" ");
        String[] yLine = reader.readLine().split(" ");
        String[] zLine = reader.readLine().split(" ");

        int[] X = new int[m];
        int[] Y = new int[m];
        int[] Z = new int[m];

        for (int i = 0; i < m; i++) {
            X[i] = Integer.parseInt(xLine[i]);
            Y[i] = Integer.parseInt(yLine[i]);
            Z[i] = Integer.parseInt(zLine[i]);
        }

        for (int i = 0; i < m; i++) {
            adjacencyList.addEdge(X[i], Y[i], Z[i]);
        }

        adjacencyList.printList();
    }
}

class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class List {
    Node[] head;

    public List(int n) {
        head = new Node[n];
    }

    public void addEdge(int u, int v, int w) {
        if (head[u - 1] == null) {
            head[u - 1] = new Node(new int[]{v, w});
        } else {
            Node temp = head[u - 1];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(new int[]{v, w});
        }
    }

    public void printList() {
        for (int i = 0; i < head.length; i++) {
            Node temp = head[i];
            System.out.print((i + 1) + ": ");
            while (temp != null) {
                int[] data = (int[]) temp.data;
                System.out.print("(" + data[0] + ", " + data[1] + ") ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}