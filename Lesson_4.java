import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Lesson_4 {
    static Integer[] list = new Integer[12];
    static final int a = 6;



    public static void main(String[] args) throws IOException {

        // task 1

        for (int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 10);
        }
        String array = Arrays.toString(list);
        System.out.println(array);
        IndexOfFirstOccurrence index = () -> {
            for (int i = 0; i < list.length; i++) {
            if (a == list[i]) {
                return i;
            }
        }
            return -1;
        };
        System.out.println(index.findIndex());


        // task 2
        ReverseString reverseString = s -> {
            String reversed = "";
            for (int i = s.length() - 1; i >= 0; i--) {
                reversed = reversed + s.charAt(i);
            }
          return reversed;
        };
        System.out.println(reverseString.reverse("java interview"));

        // task 3
        Integer[] arr = {6, 45, 26, 43, 5, 2, 12};
        MaxInteger maxInt = () -> {
            if (arr.length == 0) {
                throw new IllegalArgumentException();
            }
            int max = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            return max;
        };

        System.out.println(maxInt.maximum());

        // task 4
        TheAverage theAverage = list1 -> {

            if (list1.size() == 0) {
                throw new IllegalArgumentException();
            }
            int sum = 0;
            for (Integer s : list1) { //проходим по элементам списка и приплюсовываем каждое значение, полученное по индексу, к сумме.
                sum += s;
            }
            return (double) sum / list1.size();
        };

        List<Integer> numList = new ArrayList<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            numList.add((int) (Math.random() * 10));
        }

        System.out.println(theAverage.average(numList));

        // task 5
        Filter filter = list2 -> {

            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list2.size(); i++) {
                if (list2.get(i).charAt(0) == 'a' && list2.get(i).length() == 3) {
                    newList.add(list2.get(i));
                }
            }
            return newList;
        };
         /*не разобрался, как можно передать list2*/
        System.out.println(filter.search(Arrays.asList("Arnold", " ", "has", " ", "strong", " ", "abdominal")));
    }

}
