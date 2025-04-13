import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class GraphMetamorphosis {
    private int[][] matrix;

    public GraphMetamorphosis(int n) {
        matrix = new int[n][n];
    }

    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        GraphMetamorphosis graph = new GraphMetamorphosis(n);
        
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            int edgeCount = Integer.parseInt(input[0]);
            for (int j = 1; j <= edgeCount; j++) {
                graph.addEdge(i, Integer.parseInt(input[j]));
            }
        }
        graph.printMatrix();
    }
}