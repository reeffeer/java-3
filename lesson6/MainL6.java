package lesson6;

import java.util.Arrays;

public class MainL6 {

    public static void main(String[] args) {

        task2(new int[] {2, 4, 5, 6, 2, 4, 7, 8, 0});
        task3(new int[] {1, 4, 4, 1, 3, 1});

    }


    public static int[] task2(int[] array) {

        int n = 0;
        String str = Arrays.toString(array);
        if (str.contains(String.valueOf(4))) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 4) {
                    n = i;
                }
            }
            int[] newArray = new int[array.length - n - 1];
            System.arraycopy(array, n + 1, newArray, 0, newArray.length);
            System.out.println(Arrays.toString(newArray));
            return newArray;
        } else {
            throw new RuntimeException("4 не найдена");
        }
    }

    public static boolean task3(int[] arr) {
        boolean only4and1 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1 || arr[i] == 4) {
                only4and1 = true;
            } else {
                return false;
            }
        }
        return only4and1;
    }

}
