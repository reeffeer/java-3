import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> box = new ArrayList<>();

    public Box() {
    }

    //кладем фрукты в коробку
    public void addFruit(T fruit, int quantity) {
        for (int i = 0; i < quantity; i++) {
            box.add(fruit);
        }
    }

    //вес коробки с фруктами
    public float getWeight() {
        float weight = 0.0f;
        for (T o: box) {
            weight += o.getWeight();
        }
        return weight;
    }

    //сравнение коробок
    public boolean compare(Box anotherBox) {
        if (getWeight() == anotherBox.getWeight()) {
            System.out.println("Вес коробок одинаков.");
            return true;
        } else {
            System.out.println("Коробки различаются по весу.");
            return false;
        }
    }

    //перекладываем фрукты из одной коробки в другую
    public void pourToAnotherBox(Box<T> anotherBox) {
        anotherBox.box.addAll(box);
        box.clear();
    }

    //покажем содержимое коробки
    public void showBox() {
        System.out.println(box.toString());
    }

}
