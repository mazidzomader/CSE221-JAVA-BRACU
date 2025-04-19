import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Can_you_Traverse_Two {
    static void DFSVisit(AdjacencyList G, Node u) {
        u.colour = "Grey";
        System.out.print(u.data + " ");
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (v.colour.equals("White")) {
                DFSVisit(G, v);
            }
            temp = temp.next;
        }
        u.colour = "Black";
    }

    static void DFS(AdjacencyList G) {
        for (Node u : G.nodes) {
            u.colour = "White";
        }
        for (Node u : G.nodes) {
            if (u.colour.equals("White")) {
                DFSVisit(G, u);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        
        AdjacencyList obj = new AdjacencyList(n);
        String[] x = br.readLine().split(" ");
        String[] y = br.readLine().split(" ");
        
        for (int i = 0; i < m; i++) {
            obj.addEdge(Integer.parseInt(x[i]), Integer.parseInt(y[i]));
        }

        DFS(obj);
    }
}

class Node {
    int data;
    Node next;
    String colour;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.colour = "White";
    }
}

class AdjacencyList {
    Node[] head;
    Node[] nodes;

    AdjacencyList(int n) {
        head = new Node[n];
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    void addEdge(int u, int v) {
        Node newNodeV = new Node(v);
        newNodeV.next = head[u - 1];
        head[u - 1] = newNodeV;

        Node newNodeU = new Node(u);
        newNodeU.next = head[v - 1];
        head[v - 1] = newNodeU;
    }
}
