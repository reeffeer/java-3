package lesson7;

import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Class<L7Class> classClass = L7Class.class;
        L7Class testObj = classClass.getDeclaredConstructor().newInstance();
        Method[] methods = classClass.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        List<Method> all_methods = new ArrayList<>();
        Method methodBefore = null;
        Method methodAfter = null;
        for (Method method : classClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                all_methods.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (methodBefore == null) {
                    methodBefore = method;
                } else {
                    throw new RuntimeException("More than one method with BeforeSuite annotation");
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methodAfter == null) {
                    methodAfter = method;
                } else {
                    throw new RuntimeException("More than one method with AfterSuite annotation");
                }
            }
            all_methods.sort((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());
        }

        if (methodBefore != null) {
            methodBefore.invoke(testObj, null);
        }
        for(Method method : all_methods) {
            method.invoke(testObj, null);
        }
        if (methodAfter != null) {
            methodAfter.invoke(testObj, null);
        }

    }
}
