import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class A_Adivising {
    static Node topoSort(AdjacencyList G) {
        Node L = null;
        for (Node u : G.nodes) {
            if (!u.visited) {
                L = dfs(G, u, L);
            }
        }
        return L;
    }

    static Node dfs(AdjacencyList G, Node u, Node L) {
        u.visited = true;
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (!v.visited) {
                L = dfs(G, v, L);
            }
            temp = temp.next;
        }
        L = new Node(u.data, L);
        return L;
    }

    static boolean cycleDetector(AdjacencyList G) {
        for (Node u : G.nodes) {
            if (u.color.equals("White")) {
                if (dfsCycle(G, u)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean dfsCycle(AdjacencyList G, Node u) {
        u.color = "Gray";
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (v.color.equals("White")) {
                if (dfsCycle(G, v)) {
                    return true;
                }
            } else if (v.color.equals("Gray")) {
                return true;
            }
            temp = temp.next;
        }
        u.color = "Black";
        return false;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        AdjacencyList G = new AdjacencyList(n);
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            G.addEdge(u, v);
        }
        if (cycleDetector(G)) {
            System.out.println(-1);
        } else {
            Node L = topoSort(G);
            while (L != null) {
                System.out.print(L.data + " ");
                L = L.next;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
class Node {
    int data;
    Node next;
    boolean visited;
    String color;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
        this.visited = false;
        this.color = "White";
    }
}

class AdjacencyList {
    Node[] head;
    Node[] nodes;

    AdjacencyList(int n) {
        head = new Node[n];
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, null);
        }
    }

    void addEdge(int u, int v) {
        Node newNodeV = new Node(v, head[u - 1]);
        head[u - 1] = newNodeV;
    }
}
