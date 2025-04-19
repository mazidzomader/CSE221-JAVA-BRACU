import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cycle_Detection {
    static boolean DFSVisit(AdjacencyList G, Node u) {
        u.colour = "Grey";
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (v.colour.equals("White")) {
                if (DFSVisit(G, v)) {
                    return true;
                }
            } else if (v.colour.equals("Grey")) {
                return true;
            }
            temp = temp.next;
        }
        u.colour = "Black";
        return false;
    }

    static void DFS(AdjacencyList G) {
        for (Node u : G.nodes) {
            u.colour = "White";
        }
        for (Node u : G.nodes) {
            if (u.colour.equals("White")) {
                if (DFSVisit(G, u)) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        AdjacencyList obj = new AdjacencyList(n);

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            obj.addEdge(x, y);
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
    }
}
