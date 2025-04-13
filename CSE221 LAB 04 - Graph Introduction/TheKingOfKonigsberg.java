import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheKingOfKonigsberg {
    private int boardSize;
    private int[] kingPosition;

    public TheKingOfKonigsberg(int n) {
        this.boardSize = n;
        this.kingPosition = new int[2];
    }

    public void placeOfTheKing(int row, int col) {
        this.kingPosition[0] = row - 1;
        this.kingPosition[1] = col - 1;
    }

    public void validKingDirection() {
        ArrayList<int[]> temp = new ArrayList<>();
        int row = this.kingPosition[0];
        int col = this.kingPosition[1];
        temp.add(new int[]{row - 1, col - 1});
        temp.add(new int[]{row - 1, col});
        temp.add(new int[]{row - 1, col + 1});
        temp.add(new int[]{row, col - 1});
        temp.add(new int[]{row, col + 1});
        temp.add(new int[]{row + 1, col - 1});
        temp.add(new int[]{row + 1, col});
        temp.add(new int[]{row + 1, col + 1});
        
        ArrayList<int[]> valid = new ArrayList<>();
        for (int[] pos : temp) {
            if (pos[0] >= 0 && pos[0] < this.boardSize && pos[1] >= 0 && pos[1] < this.boardSize) {
                valid.add(new int[]{pos[0] + 1, pos[1] + 1});
            }
        }
        
        int n = valid.size();
        System.out.println(n);
        for (int[] pos : valid) {
            System.out.println(pos[0] + " " + pos[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] positionInput = reader.readLine().split(" ");
        int row = Integer.parseInt(positionInput[0]);
        int col = Integer.parseInt(positionInput[1]);

        TheKingOfKonigsberg obj = new TheKingOfKonigsberg(n);
        obj.placeOfTheKing(row, col);
        obj.validKingDirection();
    }
}