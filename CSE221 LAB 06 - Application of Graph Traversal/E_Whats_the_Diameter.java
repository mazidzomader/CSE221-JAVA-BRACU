import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;


public class E_Whats_the_Diameter {
    public static Node bfs(AdjacencyList G, Node startNode) {
        for (Node node : G.nodes) {
            node.colour = 0;
            node.distance = -1;
            node.predecessor = null;
        }

        Queue<Integer> Q = new LinkedList<>();
        startNode.colour = 1;
        startNode.distance = 0;
        Q.add(startNode.data);

        Node farthestNode = startNode;
        int maxDistance = 0;

        while (!Q.isEmpty()) {
            int uData = Q.poll();
            Node u = G.nodes[uData - 1];

            if (u.distance > maxDistance) {
                maxDistance = u.distance;
                farthestNode = u;
            }

            Node temp = G.head[u.data - 1];
            while (temp != null) {
                Node v = G.nodes[temp.data - 1];
                if (v.colour == 0) {
                    v.colour = 1;
                    v.distance = u.distance + 1;
                    v.predecessor = u;
                    Q.add(v.data);
                }
                temp = temp.next;
            }
        }

        return farthestNode;
    }

    public static int[] reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(node.data);
            node = node.predecessor;
        }
        int[] result = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            result[i] = path.get(path.size() - 1 - i);
        }
        return result;
    }

    public static void findDiameter(AdjacencyList G, Node startNode) {
        Node farthestNodeFromStart = bfs(G, startNode);
        Node farthestNodeFromFirstBfs = bfs(G, farthestNodeFromStart);
        int diameter = farthestNodeFromFirstBfs.distance;
        int[] path = reconstructPath(farthestNodeFromFirstBfs);
        System.out.println(diameter);
        System.out.println(path[0] + " " + path[path.length - 1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        AdjacencyList G = new AdjacencyList(N);

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            G.addEdge(u, v);
        }

        Node startNode = G.nodes[0];
        findDiameter(G, startNode);
    }
}

class Node {
    int data;
    Node next;
    int colour;
    int distance;
    Node predecessor;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.colour = 0;
        this.distance = -1;
        this.predecessor = null;
    }
}

class AdjacencyList {
    Node[] head;
    Node[] nodes;

    public AdjacencyList(int n) {
        head = new Node[n];
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    public void addEdge(int u, int v) {
        Node newNodeV = new Node(v);
        newNodeV.next = head[u - 1];
        head[u - 1] = newNodeV;

        Node newNodeU = new Node(u);
        newNodeU.next = head[v - 1];
        head[v - 1] = newNodeU;
    }
}
