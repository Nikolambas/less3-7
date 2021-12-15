import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try {
            try {
                startTest(new MainTest());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    static <T> void startTest(T testClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        int countBefore = 0;
        int countAfter = 0;
        Method[] method = testClass.getClass().getDeclaredMethods();
        for (Method m : method) {
            if (m.getAnnotation(BeforeSuite.class) != null) {
                countBefore++;
                continue;
            }
            if (m.getAnnotation(AfterSuite.class) != null) {
                countAfter++;
            }
        }
        if (countBefore > 1 || countAfter > 1) {
            throw new RuntimeException();
        }
        testClass.getClass().getMethod("beforeSuite").invoke(testClass);
        for (int i = 10; 0 < i; i--) {
            for (Method m : method) {
                if (m.getAnnotation(Test.class)!=null&&m.getAnnotation(Test.class).priority() == i) {
                    m.invoke(testClass);
                }
            }
        }
        testClass.getClass().getMethod("beforeSuite").invoke(testClass);
    }
}
