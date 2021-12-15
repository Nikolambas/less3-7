import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MainTest {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite");
    }
    @Test(priority = 6)
    public void test1(){
        System.out.println("Test1");
    }
    @Test(priority = 1)
    public void test2(){
        System.out.println("Test2");
    }
    @Test(priority = 7)
    public void test3(){
        System.out.println("Test3");
    }
}
