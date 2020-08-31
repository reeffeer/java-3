import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lesson_1 {

    public static void main(String[] args) {

        //Task 1
        System.out.println("Task 1");
        String[] array = {"A", "B", "N"};
        replace(array, 0, 2);

        //Task 2
        System.out.println("Task 2");
        List<String> arrayList = new ArrayList();
        toArrayList(array, arrayList);

        //Task 3
        System.out.println("Task 3");
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        orangeBox1.addFruit(new Orange(), 30);
        appleBox1.addFruit(new Apple(), 50);

        orangeBox1.compare(appleBox1);

        orangeBox1.showBox();
        orangeBox1.pourToAnotherBox(orangeBox2);
        orangeBox1.showBox();
        orangeBox2.showBox();

        appleBox1.showBox();
        appleBox1.pourToAnotherBox(appleBox2);
        appleBox1.showBox();
        appleBox2.showBox();

    }


    //Task 1
    public static void replace(String[] array, int n1, int n2) {
        String tmp = array[n1];
        array[n1] = array[n2];
        array[n2] = tmp;
        System.out.println(Arrays.toString(array));
    }

    //Task 2
    public static void toArrayList(String[] array, List arrayList) {
        Collections.addAll(arrayList, array);
        //для проверки
        arrayList.add("C");
        System.out.println(arrayList);
        System.out.println(Arrays.toString(array));
    }


}
