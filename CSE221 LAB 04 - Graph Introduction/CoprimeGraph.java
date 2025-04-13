import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CoprimeGraph {
    private List<List<Integer>> graph;
    private List<Integer> result;

    public CoprimeGraph(int n) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (gcd(i + 1, j + 1) == 1) {
                        graph.get(i).add(j + 1);
                    }
                }
            }
        }
        result = new ArrayList<>();
    }

    public void queriesValidity(int u, int v) {
        List<Integer> temp = graph.get(u - 1);
        if (v <= temp.size()) {
            result.add(temp.get(v - 1));
        } else {
            result.add(-1);
        }
    }

    public List<Integer> getResult() {
        return result;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        CoprimeGraph obj = new CoprimeGraph(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            obj.queriesValidity(u, v);
        }
        for (int i : obj.getResult()) {
            System.out.println(i);
        }
    }
}