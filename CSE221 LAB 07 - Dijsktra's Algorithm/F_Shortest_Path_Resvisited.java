import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class F_Shortest_Path_Resvisited {
    public static int secondShortestPath(Graph G, int S, int D, int N) {
        int[][] dist = new int[N][2];
        for (int i = 0; i < N; i++) {
            dist[i][0] = Integer.MAX_VALUE;
            dist[i][1] = Integer.MAX_VALUE;
        }
        dist[S - 1][0] = 0;

        PriorityQueue<int[]> temp = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        temp.offer(new int[]{0, S});

        while (!temp.isEmpty()) {
            int[] current = temp.poll();
            int d_u = current[0];
            int u = current[1];

            if (d_u > dist[u - 1][1]) {
                continue;
            }

            for (int[] edge : G.node.get(u - 1)) {
                int v = edge[0];
                int w = edge[1];
                int new_dist = d_u + w;

                if (new_dist < dist[v - 1][0]) {
                    dist[v - 1][1] = dist[v - 1][0];
                    dist[v - 1][0] = new_dist;
                    temp.offer(new int[]{new_dist, v});
                } else if (dist[v - 1][0] < new_dist && new_dist < dist[v - 1][1]) {
                    dist[v - 1][1] = new_dist;
                    temp.offer(new int[]{new_dist, v});
                }
            }
        }

        return dist[D - 1][1] == Integer.MAX_VALUE ? -1 : dist[D - 1][1];
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int S = Integer.parseInt(firstLine[2]);
        int D = Integer.parseInt(firstLine[3]);

        Graph G = new Graph(N);
        for (int i = 0; i < M; i++) {
            String[] edgeLine = br.readLine().split(" ");
            int u = Integer.parseInt(edgeLine[0]);
            int v = Integer.parseInt(edgeLine[1]);
            int w = Integer.parseInt(edgeLine[2]);
            G.addEdge(u, v, w);
        }

        System.out.println(secondShortestPath(G, S, D, N));
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

    public void addEdge(int u, int v, int w) {
        node.get(u - 1).add(new int[]{v, w});
        node.get(v - 1).add(new int[]{u, w});
    }
}
