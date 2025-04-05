import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Trains {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<String[]> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String trainDetails = reader.readLine();
            data.add(modification(trainDetails));
        }

        List<String[]> afterRearrangement = unknownStableSort(data, n);

        for (String[] train : afterRearrangement) {
            System.out.println(train[3]);
        }
    }

    public static String[] modification(String trainDetails) {
        String[] temp = trainDetails.split(" ");
        String name = temp[0];
        String destination = temp[4];
        String time = temp[6];
        return new String[]{name, destination, time, trainDetails};
    }

    public static int timeToMinuteConverter(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3));
        return hour * 60 + minute;
    }

    public static List<String[]> unknownStableSort(List<String[]> data, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j)[0].compareTo(data.get(j + 1)[0]) > 0 ||
                    (data.get(j)[0].equals(data.get(j + 1)[0]) &&
                     timeToMinuteConverter(data.get(j)[2]) < timeToMinuteConverter(data.get(j + 1)[2]))) {
                    String[] temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
        return data;
    }
}
