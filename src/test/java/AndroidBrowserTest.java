import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class AndroidBrowserTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "6.0.1");
        //启动模拟器自带的浏览器,设置browserName 和可用Chromedriver的路径 chromedriverExecutable
        desiredCapabilities.setCapability("browserName", "Browser");
        desiredCapabilities.setCapability("chromedriverExecutable", "C:\\Users\\HJY\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //可选参数
        //不要重置签名
        desiredCapabilities.setCapability("noSign", true);
        //不清除缓存
        desiredCapabilities.setCapability("noReset", true);

        //设置使用unicode键盘，让自动化脚本实现过程中能够输入中文，成对使用，单独使用下面的某一个属性会ignore
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(5000);
        //打开H5页面
        driver.get("https://ui.ptlogin2.qq.com/cgi-bin/login?pt_hide_ad=1&style=9&appid=549000929&pt_no_auth=1&pt_wxtest=1&daid=5&s_url=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex");

        System.out.println(driver.getWindowHandle());
        //获取所有handles
        Set<String> handles = driver.getContextHandles();
        for (String s : handles) {
            System.out.println(s);
        }
        //使用selenium定位
        driver.findElement(By.id("u")).sendKeys("123456");

        //切换为NATIVE_APP，使用appium定位
        driver.context("NATIVE_APP");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.webkit.WebView[@content-desc=\"手机统一登录\"]/android.widget.ListView/android.view.View[2]/android.widget.EditText")).sendKeys("222222");

        //切回CHROMIUN,使用selenium定位
        driver.context("CHROMIUM");
        Thread.sleep(3000);
        driver.findElement(By.id("go")).click();

        Thread.sleep(10000);

        driver.closeApp();

        driver.quit();


    }
}
