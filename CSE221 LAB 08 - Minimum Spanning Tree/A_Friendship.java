import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class A_Friendship {
    private static int[] parent;
    private static int[] rank;
    private static int[] size;
    private static ArrayList<Integer> result = new ArrayList<>();

    public static void MakeSet(int v) {
        parent[v] = v;
        rank[v] = 0;
        size[v] = 1;
    }


    public static int Find(int v) {
        if (parent[v] != v) {
            parent[v] = Find(parent[v]); 
        }
        return parent[v];
    }


    public static void Union(int u, int v) {
        int root_u = Find(u);
        int root_v = Find(v);

        if (root_u == root_v) {
            result.add(size[root_u]);
            return;
        }

        if (rank[root_u] < rank[root_v]) {
            parent[root_u] = root_v;
            size[root_v] += size[root_u];
            result.add(size[root_v]);
        } else if (rank[root_u] > rank[root_v]) {
            parent[root_v] = root_u;
            size[root_u] += size[root_v];
            result.add(size[root_u]);
        } else {
            parent[root_v] = root_u;
            rank[root_u]++;
            size[root_u] += size[root_v];
            result.add(size[root_u]);
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        int k = fr.nextInt();


        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];


        for (int i = 1; i <= n; i++) {
            MakeSet(i);
        }

        for (int i = 0; i < k; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            Union(a, b);
        }


        StringBuilder sb = new StringBuilder();
        for (int s : result) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}