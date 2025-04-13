import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class EdgeQueries {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        String[] oneEndInput = reader.readLine().split(" ");
        String[] otherEndInput = reader.readLine().split(" ");

        EdgeQuerie edgeQueries = new EdgeQuerie(n);
        for (int i = 0; i < m; i++) {
            int u = Integer.parseInt(oneEndInput[i]);
            int v = Integer.parseInt(otherEndInput[i]);
            edgeQueries.addEdge(u, v);
        }

        for (Node node : edgeQueries.getNodes()) {
            System.out.print((node.inDegree - node.outDegree) + " ");
        }
    }
}

class Node {
    int value;
    int inDegree;
    int outDegree;
    Node next;

    public Node(int n) {
        this.value = n;
        this.inDegree = 0;
        this.outDegree = 0;
        this.next = null;
    }
}

class EdgeQuerie {
    List<Node> nodes;

    public EdgeQuerie(int n) {
        nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(i));
        }
    }

    public void addEdge(int u, int v) {
        nodes.get(u - 1).outDegree++;
        nodes.get(v - 1).inDegree++;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}