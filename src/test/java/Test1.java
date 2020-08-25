import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
    @Test
    public static void test() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        PageObject pageObject = new PageObject(driver);
        pageObject.search();
        Assert.assertTrue(driver.getTitle().contains("百度"), "断言失败");
    }

    public static int add(int... x) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum = sum + x[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(1, 2, 3, 4, 9, 5));
    }
}
