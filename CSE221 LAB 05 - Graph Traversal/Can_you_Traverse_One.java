import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Can_you_Traverse_One {
    public static void bfs(AdjacencyList G, Node startNode) {
        for (Node node : G.nodes) {
            node.colour = 0;
        }

        Queue<Node> Q = new LinkedList<>();
        startNode.colour = 1;
        Q.add(startNode);

        while (!Q.isEmpty()) {
            Node u = Q.poll();
            System.out.print(u.data + " ");

            Node temp = G.head[u.data - 1];
            while (temp != null) {
                Node v = G.nodes[temp.data - 1];
                if (v.colour == 0) {
                    v.colour = 1;
                    Q.add(v);
                }
                temp = temp.next;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        AdjacencyList obj = new AdjacencyList(n);

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int X = Integer.parseInt(edge[0]);
            int Y = Integer.parseInt(edge[1]);
            obj.addEdge(X, Y);
        }

        bfs(obj, obj.nodes[0]);
    }
}
class Node {
    int data;
    Node next;
    int colour;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.colour = 0;
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

    public void printList() {
        for (int i = 0; i < head.length; i++) {
            Node temp = head[i];
            System.out.print((i + 1) + ": ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}