import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class C_Again_MST {
    public static int secondMST(int V, List<int[]> edges) {
        edges.sort((a, b) -> Integer.compare(a[2], b[2]));
        DSU dsu = new DSU(V);
        int mstCost = 0;
        List<Integer> mstEdges = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < edges.size(); i++) {
            int[] edge = edges.get(i);
            int x = edge[0], y = edge[1], w = edge[2];
            if (dsu.find(x) != dsu.find(y)) {
                dsu.unite(x, y);
                mstCost += w;
                mstEdges.add(i);
                count++;
                if (count == V - 1) {
                    break;
                }
            }
        }

        if (count < V - 1) {
            return -1;
        }

        int res = Integer.MAX_VALUE;

        for (int excludeIdx : mstEdges) {
            dsu = new DSU(V);
            int newCost = 0;
            int newCount = 0;

            for (int i = 0; i < edges.size(); i++) {
                if (i == excludeIdx) {
                    continue;
                }
                int[] edge = edges.get(i);
                int x = edge[0], y = edge[1], w = edge[2];
                if (dsu.find(x) != dsu.find(y)) {
                    dsu.unite(x, y);
                    newCost += w;
                    newCount++;
                    if (newCount == V - 1) {
                        break;
                    }
                }
            }

            if (newCount == V - 1 && newCost > mstCost) {
                res = Math.min(res, newCost);
            }
        }

        for (int includeIdx = 0; includeIdx < edges.size(); includeIdx++) {
            if (mstEdges.contains(includeIdx)) {
                continue;
            }
            dsu = new DSU(V);
            int newCost = 0;
            int newCount = 0;
            int[] edge = edges.get(includeIdx);
            int x = edge[0], y = edge[1], w = edge[2];
            dsu.unite(x, y);
            newCost += w;
            newCount++;

            for (int i = 0; i < edges.size(); i++) {
                if (i == includeIdx) {
                    continue;
                }
                edge = edges.get(i);
                x = edge[0];
                y = edge[1];
                w = edge[2];
                if (dsu.find(x) != dsu.find(y)) {
                    dsu.unite(x, y);
                    newCost += w;
                    newCount++;
                    if (newCount == V - 1) {
                        break;
                    }
                }
            }

            if (newCount == V - 1 && newCost > mstCost) {
                res = Math.min(res, newCost);
            }
        }

        return res != Integer.MAX_VALUE ? res : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] edgeLine = br.readLine().split(" ");
            int u = Integer.parseInt(edgeLine[0]) - 1;
            int v = Integer.parseInt(edgeLine[1]) - 1;
            int w = Integer.parseInt(edgeLine[2]);
            edges.add(new int[]{u, v, w});
        }

        System.out.println(secondMST(N, edges));
    }
}
class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        int root = find(parent[i]);
        parent[i] = root;
        return root;
    }

    public void unite(int x, int y) {
        int s1 = find(x);
        int s2 = find(y);
        if (s1 != s2) {
            if (rank[s1] < rank[s2]) {
                parent[s1] = s2;
            } else if (rank[s1] > rank[s2]) {
                parent[s2] = s1;
            } else {
                parent[s2] = s1;
                rank[s1]++;
            }
        }
    }
}

