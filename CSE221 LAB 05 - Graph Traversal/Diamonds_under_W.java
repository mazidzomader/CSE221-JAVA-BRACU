import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Diamonds_under_W {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);
        
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().strip().toCharArray();
        }

        boolean[][] status = new boolean[n][m];
        int diamond = 0;

        for (int i = 0; i < n; i++) {
            boolean hasDiamond = false;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'D') {
                    hasDiamond = true;
                    break;
                }
            }
            if (!hasDiamond) {
                continue;
            }

            for (int j = 0; j < m; j++) {
                if (!status[i][j] && grid[i][j] != '#') {
                    int count = DFS_visit(grid, i, j, status);
                    diamond = Math.max(diamond, count);
                }
            }
        }

        System.out.println(diamond);
    }

    public static int DFS_visit(char[][] grid, int row, int col, boolean[][] status) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col});
        int count = 0;

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int r = pos[0];
            int c = pos[1];

            if (status[r][c]) {
                continue;
            }
            status[r][c] = true;

            if (grid[r][c] == 'D') {
                count++;
            }

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions) {
                int new_row = r + direction[0];
                int new_col = c + direction[1];
                if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length) {
                    if (!status[new_row][new_col] && grid[new_row][new_col] != '#') {
                        stack.push(new int[]{new_row, new_col});
                    }
                }
            }
        }
        return count;
    }
}