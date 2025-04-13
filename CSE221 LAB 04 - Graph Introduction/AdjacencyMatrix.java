import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AdjacencyMatrix {
    private int[][] matrix;

    public AdjacencyMatrix(int n) {
        matrix = new int[n][n];
    }

    public void addEdge(int u, int v, int w) {
        matrix[u - 1][v - 1] = w;
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
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        
        AdjacencyMatrix obj = new AdjacencyMatrix(n);
        for (int i = 0; i < m; i++) {
            String[] edgeLine = reader.readLine().split(" ");
            int U = Integer.parseInt(edgeLine[0]);
            int V = Integer.parseInt(edgeLine[1]);
            int W = Integer.parseInt(edgeLine[2]);
            obj.addEdge(U, V, W);
        }
        obj.printMatrix();
    }
}