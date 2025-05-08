import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;



public class C_Minimize_the_Danger {
    public static int[] Dijkstra(Graph G, int S, int N) {
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[S - 1] = 0;
        PriorityQueue<int[]> Q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Q.add(new int[]{0, S});

        while (!Q.isEmpty()) {
            int[] temp = Q.poll();
            int temp_u = temp[0];
            int u = temp[1];
            if (temp_u > dist[u - 1]) {
                continue;
            }
            for (int[] edge : G.node.get(u - 1)) {
                int v = edge[0];
                int w = edge[1];
                int temp_v = Math.max(temp_u, w);
                if (temp_v < dist[v - 1]) {
                    dist[v - 1] = temp_v;
                    Q.add(new int[]{temp_v, v});
                }
            }
        }

        return dist;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        Graph G = new Graph(N);

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int X = Integer.parseInt(line[0]);
            int Y = Integer.parseInt(line[1]);
            int Z = Integer.parseInt(line[2]);
            G.addEdge(X, Y, Z);
        }

        int[] dist = Dijkstra(G, 1, N);
        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
        System.out.println();
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
        node.get(d - 1).add(new int[]{s, w});
    }
}
