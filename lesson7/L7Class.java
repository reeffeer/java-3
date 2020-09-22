package lesson7;

public class L7Class {
    @BeforeSuite
    public void init() {
        System.out.println("init");
    }

    @Test(priority = 3)
    public void test_uno() {
        System.out.println("test_uno");
    }

    @Test(priority = 7)
    public void test_dos() {
        System.out.println("test_dos");
    }

    @Test(priority = 1)
    public void test_tres() {
        System.out.println("test_tres");
    }

    @Test(priority = 4)
    public void test_cuatro() {
        System.out.println("test_cuatro");
    }

    public void any_method() {
        System.out.println("any_method");
    }

    @AfterSuite
    public void end() {
        System.out.println("end");
    }
}
