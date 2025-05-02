import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class D_Easy_Tree_Queries {
    static void DFSVisit(AdjacencyList G, int uIndex) {
        Node u = G.nodes[uIndex];
        u.colour = "Grey";
        G.subtreeSize[uIndex] = 1;

        for (int vIndex : G.adj.get(uIndex)) {
            Node v = G.nodes[vIndex];
            if (v.colour.equals("White")) {
                DFSVisit(G, vIndex);
                G.subtreeSize[uIndex] += G.subtreeSize[vIndex];
            }
        }
        u.colour = "Black";
    }

    static void DFS(AdjacencyList G, int rootIndex) {
        for (Node u : G.nodes) {
            u.colour = "White";
        }
        DFSVisit(G, rootIndex);
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int r = Integer.parseInt(firstLine[1]);

        AdjacencyList G = new AdjacencyList(n);

        for (int i = 0; i < n - 1; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            G.addEdge(u, v);
        }

        DFS(G, r - 1);

        int q = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(br.readLine());
            result.append(G.subtreeSize[x - 1]).append("\n");
        }
        System.out.print(result);
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
class Node {
    int data;
    String colour;

    Node(int data) {
        this.data = data;
        this.colour = "White";
    }
}

class AdjacencyList {
    List<List<Integer>> adj;
    Node[] nodes;
    int[] subtreeSize;

    AdjacencyList(int n) {
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        subtreeSize = new int[n];
    }

    void addEdge(int u, int v) {
        adj.get(u - 1).add(v - 1);
        adj.get(v - 1).add(u - 1);
    }
}
