import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sorting_Again {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int no_of_students = Integer.parseInt(reader.readLine());
        
        StringTokenizer st = new StringTokenizer(reader.readLine());
        String[] student_id = new String[no_of_students];
        for (int i = 0; i < no_of_students; i++) {
            student_id[i] = st.nextToken();
        }
        
        st = new StringTokenizer(reader.readLine());
        String[] student_marks = new String[no_of_students];
        for (int i = 0; i < no_of_students; i++) {
            student_marks[i] = st.nextToken();
        }

        int count = 0;

        for (int index = 0; index < no_of_students - 1; index++) {
            int minimum_index = index;
            for (int index1 = index + 1; index1 < no_of_students; index1++) {
                if ((Integer.parseInt(student_marks[index1]) == Integer.parseInt(student_marks[minimum_index]) && 
                     Integer.parseInt(student_id[index1]) < Integer.parseInt(student_id[minimum_index])) || 
                    (Integer.parseInt(student_marks[index1]) > Integer.parseInt(student_marks[minimum_index]))) {
                    minimum_index = index1;
                }
            }
            if (minimum_index != index) {
                String tempMark = student_marks[index];
                student_marks[index] = student_marks[minimum_index];
                student_marks[minimum_index] = tempMark;

                String tempId = student_id[index];
                student_id[index] = student_id[minimum_index];
                student_id[minimum_index] = tempId;

                count++;
            }
        }

        System.out.println("Minimum swaps: " + count);
        for (int i = 0; i < no_of_students; i++) {
            System.out.println("ID: " + student_id[i] + " Mark: " + student_marks[i]);
        }
    }
}
