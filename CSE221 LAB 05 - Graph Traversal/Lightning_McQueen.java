import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Lightning_McQueen {
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
        java.util.Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int s = Integer.parseInt(firstLine[2]);
        int d = Integer.parseInt(firstLine[3]);

        AdjacencyList obj = new AdjacencyList(n);
        String[] x = br.readLine().split(" ");
        String[] y = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            obj.addEdge(Integer.parseInt(x[i]), Integer.parseInt(y[i]));
        }

        obj.sortAdj();
        bfs(obj, obj.nodes[s - 1]);
        List<Integer> path = pathFinder(obj.nodes[d - 1]);

        if (obj.nodes[d - 1].colour == 0) {
            System.out.println(-1);
        } else {
            System.out.println(path.size() - 1);
            for (int node : path) {
                System.out.print(node + " ");
            }
        }
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
        head.get(v - 1).add(u);
    }

    public void sortAdj() {
        for (List<Integer> v : head) {
            v.sort(null);
        }
    }
}