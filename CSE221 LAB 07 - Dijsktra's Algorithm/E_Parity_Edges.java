import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class E_Parity_Edges {
    public static int Parity_Edges(Graph G, int N) {
        int[][] dist = new int[2][N];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;
        dist[1][0] = 0;

        PriorityQueue<int[]> Q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Q.add(new int[]{0, 1, -1});

        while (!Q.isEmpty()) {
            int[] current = Q.poll();
            int d_u = current[0], u = current[1], last_parity = current[2];
            for (int[] edge : G.node.get(u - 1)) {
                int v = edge[0], w = edge[1];
                int parity = w % 2;
                if (parity == last_parity) {
                    continue;
                } else {
                    if (d_u + w < dist[parity][v - 1]) {
                        dist[parity][v - 1] = d_u + w;
                        Q.add(new int[]{dist[parity][v - 1], v, parity});
                    }
                }
            }
        }

        int ans = Math.min(dist[0][N - 1], dist[1][N - 1]);
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        
        Graph G = new Graph(N);
        String[] u = br.readLine().split(" ");
        String[] v = br.readLine().split(" ");
        String[] w = br.readLine().split(" ");
        
        for (int i = 0; i < M; i++) {
            G.add_edge(Integer.parseInt(u[i]), Integer.parseInt(v[i]), Integer.parseInt(w[i]));
        }
        System.out.println(Parity_Edges(G, N));
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

    public void add_edge(int s, int d, int w) {
        node.get(s - 1).add(new int[]{d, w});
    }
}

