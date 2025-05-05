import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections; 

public class A_Shortest_Path {
    public static int[] Dijkstra(Graph G, int S, int D, int N) {
        int[] dist = new int[N];
        Integer[] prev = new Integer[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
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
                    prev[v - 1] = u;
                    Q.offer(new int[]{dist[v - 1], v});
                }
            }
        }

        if (dist[D - 1] == Integer.MAX_VALUE) {
            return new int[]{-1};
        }

        ArrayList<Integer> path = new ArrayList<>();
        Integer u = D;
        while (u != null) {
            path.add(u);
            u = prev[u - 1];
        }
        Collections.reverse(path); 

        int[] result = new int[path.size() + 1];
        result[0] = dist[D - 1];
        for (int i = 0; i < path.size(); i++) {
            result[i + 1] = path.get(i);
        }
        return result;
    }

    public static void solve() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = input.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int S = Integer.parseInt(firstLine[2]);
        int D = Integer.parseInt(firstLine[3]);

        Graph G = new Graph(N);
        String[] Edge_Source = input.readLine().split(" ");
        String[] Edge_Destini = input.readLine().split(" ");
        String[] Edge_Weight = input.readLine().split(" ");

        for (int idx = 0; idx < M; idx++) {
            G.addEdge(Integer.parseInt(Edge_Source[idx]), Integer.parseInt(Edge_Destini[idx]), Integer.parseInt(Edge_Weight[idx]));
        }

        int[] result = Dijkstra(G, S, D, N);
        if (result[0] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(result[0]);
            for (int i = 1; i < result.length; i++) {
                System.out.print(result[i]);
                if (i < result.length - 1) {
                    System.out.print(" ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}

class Graph {
    ArrayList<ArrayList<int[]>> node;

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
