import com.sun.jdi.request.ThreadStartRequest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainClass {

    public static final int CARS_COUNT = 4;


    public static void main(String[] args) {

        CyclicBarrier CB = new CyclicBarrier(5);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), CB);
        }

        Thread[] threads = new Thread[cars.length];
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
                CB.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                CB.await();

            for (Thread t : threads) {
                t.join();
            }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}



