import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class B_A_Football_Match {
    public static void main(String[] args) throws IOException {
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
        System.out.println(A_Football_Match(G.head, n));
    }

    static int A_Football_Match(Node[] G, int N) {
        int[] color = new int[N + 1];
        int maxCnt = 0;

        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                Queue Q = new Queue();
                Q.enqueue(i);
                color[i] = 1;
                int cnt1 = 0;
                int cnt2 = 0;

                while (!Q.isEmpty()) {
                    int node = Q.dequeue();
                    if (color[node] == 1) {
                        cnt1++;
                    } else {
                        cnt2++;
                    }
                    Node curr = G[node - 1];
                    while (curr != null) {
                        int v = curr.data;
                        if (color[v] == 0) {
                            color[v] = (color[node] == 1) ? 2 : 1;
                            Q.enqueue(v);
                        } else if (color[v] == color[node]) {
                            return -1;
                        }
                        curr = curr.next;
                    }
                }
                maxCnt += Math.max(cnt1, cnt2);
            }
        }
        return maxCnt;
    }
}
class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class Queue {
    private List<Integer> inbox;
    private List<Integer> outbox;

    Queue() {
        inbox = new ArrayList<>();
        outbox = new ArrayList<>();
    }

    boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    void enqueue(int item) {
        inbox.add(item);
    }

    int dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.add(inbox.remove(inbox.size() - 1));
            }
        }
        return outbox.remove(outbox.size() - 1);
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
        Node nodeV = new Node(v, head[u - 1]);
        head[u - 1] = nodeV;

        Node nodeU = new Node(u, head[v - 1]);
        head[v - 1] = nodeU;
    }
}
