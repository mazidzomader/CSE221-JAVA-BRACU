import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class B_Where_to_Meet {
    public static int[] Dijkstra(Graph G, int S, int N) {
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[S - 1] = 0;
        PriorityQueue<int[]> Q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Q.offer(new int[]{0, S});

        while (!Q.isEmpty()) {
            int[] current = Q.poll();
            int d_u = current[0];
            int u = current[1];

            if (d_u > dist[u - 1]) {
                continue;
            }

            for (int[] edge : G.node.get(u - 1)) {
                int v = edge[0];
                int w = edge[1];
                if (dist[u - 1] + w < dist[v - 1]) {
                    dist[v - 1] = dist[u - 1] + w;
                    Q.offer(new int[]{dist[v - 1], v});
                }
            }
        }
        return dist;
    }

    public static void solve() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = input.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int S = Integer.parseInt(firstLine[2]);
        int T = Integer.parseInt(firstLine[3]);
        Graph G = new Graph(N);

        for (int i = 0; i < M; i++) {
            String[] edgeLine = input.readLine().split(" ");
            int X = Integer.parseInt(edgeLine[0]);
            int Y = Integer.parseInt(edgeLine[1]);
            int Z = Integer.parseInt(edgeLine[2]);
            G.addEdge(X, Y, Z);
        }

        int[] Alice_Distance = Dijkstra(G, S, N);
        int[] Bob_Distance = Dijkstra(G, T, N);

        int min_time = Integer.MAX_VALUE;
        int meet_node = -1;
        boolean found = false;

        for (int idx = 0; idx < N; idx++) {
            if (Alice_Distance[idx] == Integer.MAX_VALUE || Bob_Distance[idx] == Integer.MAX_VALUE) {
                continue;
            }
            int meet = Math.max(Alice_Distance[idx], Bob_Distance[idx]);
            if (!found || meet < min_time || (meet == min_time && idx + 1 < meet_node)) {
                min_time = meet;
                meet_node = idx + 1;
                found = true;
            }
        }

        if (!found) {
            System.out.println(-1);
        } else {
            System.out.println(min_time + " " + meet_node);
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
class Graph {
    List<List<int[]>> node;

    public Graph(int n) {
        node = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            node.add(new ArrayList<>());
        }
    }

    public void addEdge(int s, int d, int w) {
        node.get(s - 1).add(new int[]{d, w});
    }
}
