import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class C_The_Knight_of_Konigsberg {
    public static int minKnightMoves(int N, int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return 0;
        }

        int[][] moves = { {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1} };

        int[][] flag = new int[N][N];
        QueueCustom queue = new QueueCustom();
        queue.enqueue(new int[] { x1 - 1, y1 - 1, 0 });
        flag[x1 - 1][y1 - 1] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.dequeue();
            int x = current[0];
            int y = current[1];
            int steps = current[2];

            for (int[] move : moves) {
                int nx = x + move[0];
                int ny = y + move[1];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && flag[nx][ny] == 0) {
                    if (nx == x2 - 1 && ny == y2 - 1) {
                        return steps + 1;
                    }
                    flag[nx][ny] = 1;
                    queue.enqueue(new int[] { nx, ny, steps + 1 });
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");
        int x1 = Integer.parseInt(input[0]);
        int y1 = Integer.parseInt(input[1]);
        int x2 = Integer.parseInt(input[2]);
        int y2 = Integer.parseInt(input[3]);
        System.out.println(minKnightMoves(N, x1, y1, x2, y2));
    }
}


class QueueCustom {
    private LinkedList<int[]> inbox;
    private LinkedList<int[]> outbox;

    public QueueCustom() {
        inbox = new LinkedList<>();
        outbox = new LinkedList<>();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    public void enqueue(int[] item) {
        inbox.add(item);
    }

    public int[] dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.add(inbox.removeLast());
            }
        }
        return outbox.removeLast();
    }
}
