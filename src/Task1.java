public class Task1 {

    private final Object letter = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Thread t1 = new Thread(() -> {
           task1.printA();
        });

        Thread t2 = new Thread(() -> {
            task1.printB();
        });

        Thread t3 = new Thread(() -> {
            task1.printC();
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (letter) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        letter.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    letter.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (letter) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        letter.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    letter.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (letter) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        letter.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    letter.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
