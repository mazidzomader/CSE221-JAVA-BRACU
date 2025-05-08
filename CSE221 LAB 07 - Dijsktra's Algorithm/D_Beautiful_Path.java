import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D_Beautiful_Path {
    public static void addEdge(List<List<Integer>> G, int u, int v) {
        G.get(u - 1).add(v - 1);
    }

    public static int[] beautifulPath(List<List<Integer>> G, int N, int[] weights, int S, int D) {
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[S - 1] = weights[S - 1];

        PriorityQueue<int[]> temp = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        temp.add(new int[]{weights[S - 1], S - 1});

        while (!temp.isEmpty()) {
            int[] current = temp.poll();
            int weightU = current[0];
            int u = current[1];
            if (weightU > dist[u]) {
                continue;
            }

            for (int v : G.get(u)) {
                int newCost = weightU + weights[v];
                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    temp.add(new int[]{newCost, v});
                }
            }
        }
        return dist;
    }

    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> G = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            G.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            addEdge(G, u, v);
        }

        int[] result = beautifulPath(G, N, weights, S, D);
        if (result[D - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result[D - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}

