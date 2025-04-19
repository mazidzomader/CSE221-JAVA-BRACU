import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Through_the_Jungle {
    public static void bfs(AdjacencyList G, Node startNode) {
        for (Node node : G.nodes) {
            node.colour = 0;
            node.parent = null;
        }

        Queue<Node> Q = new LinkedList<>();
        startNode.colour = 1;
        Q.add(startNode);

        while (!Q.isEmpty()) {
            Node u = Q.poll();
            for (int V : G.head.get(u.data - 1)) {
                Node v = G.nodes[V - 1];
                if (v.colour == 0) {
                    v.colour = 1;
                    v.parent = u;
                    Q.add(v);
                }
            }
        }
    }

    public static List<Integer> pathFinder(Node D) {
        List<Integer> path = new ArrayList<>();
        while (D != null) {
            path.add(D.data);
            D = D.parent;
        }
        List<Integer> reversedPath = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            reversedPath.add(path.get(i));
        }
        return reversedPath;
    }

    public static void throughTheJungle(AdjacencyList G, int s, int d, int k) {
        if (s == d && s == k) {
            System.out.println(0);
            System.out.println(s);
            return;
        }

        bfs(G, G.nodes[s - 1]);
        if (k != s && G.nodes[k - 1].parent == null) {
            System.out.println(-1);
            return;
        }
        List<Integer> path1 = pathFinder(G.nodes[k - 1]);

        bfs(G, G.nodes[k - 1]);
        if (d != k && G.nodes[d - 1].parent == null) {
            System.out.println(-1);
            return;
        }
        List<Integer> path2 = pathFinder(G.nodes[d - 1]);

        List<Integer> path3 = new ArrayList<>(path1);
        path3.addAll(path2.subList(1, path2.size()));
        System.out.println(path3.size() - 1);
        for (int node : path3) {
            System.out.print(node + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int s = Integer.parseInt(input[2]);
        int d = Integer.parseInt(input[3]);
        int k = Integer.parseInt(input[4]);

        AdjacencyList obj = new AdjacencyList(n);

        for (int i = 0; i < m; i++) {
            String[] edgeInput = br.readLine().split(" ");
            int X = Integer.parseInt(edgeInput[0]);
            int Y = Integer.parseInt(edgeInput[1]);
            obj.addEdge(X, Y);
        }

        obj.sortAdj();
        throughTheJungle(obj, s, d, k);
    }
}

class Node {
    int data;
    int colour;
    Node parent;

    public Node(int data) {
        this.data = data;
        this.colour = 0;
        this.parent = null;
    }
}

class AdjacencyList {
    List<List<Integer>> head;
    Node[] nodes;

    public AdjacencyList(int n) {
        head = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            head.add(new ArrayList<>());
        }
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    public void addEdge(int u, int v) {
        head.get(u - 1).add(v);
    }

    public void sortAdj() {
        for (List<Integer> v : head) {
            v.sort(Integer::compareTo);
        }
    }
}
