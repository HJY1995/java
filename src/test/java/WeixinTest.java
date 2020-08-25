
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class WeixinTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("deviceName", "c8e9d4a6");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
        desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
/*
 下面的设置非常总要
 */
        // 支持X5内核应用自动化配置
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
/*
ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
把com.tencent.mm:toolsmp的webview识别成com.tencent.mm的webview.
所以为了避免这个问题，加上androidProcess: com.tencent.mm:toolsmp
 */
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "WEBVIEW_com.tencent.mm:toolsmp");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        desiredCapabilities.setBrowserName("");
        desiredCapabilities.setCapability("chromedriverExecutable", "C:\\Users\\HJY\\Downloads\\chromedriver_win32weixin\\chromedriver.exe");

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
        //找到并打开美团的微信小程序

        driver.findElement(By.id("com.tencent.mm:id/r_")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.tencent.mm:id/m7']")).sendKeys("美团");
        Thread.sleep(2000);
        driver.findElement(By.id("com.tencent.mm:id/s5")).click();
        Thread.sleep(10000);
        //切换到webview定位
        System.out.println("所有的contextHandles:" + driver.getContextHandles());
        System.out.println("切换前");
        driver.context("WEBVIEW_com.tencent.mm:toolsmp");
        System.out.println("切换后:" + driver.getContext());
        Thread.sleep(5000);

        //叉掉弹屏广告
        driver.findElement(By.xpath("//wx-view[@class='newuser--close-btn']")).click();
        Thread.sleep(1000);
        //点击美食
        driver.findElement(By.xpath("//wx-view[@data-name='美食']")).click();
        Thread.sleep(1000);
        //切换到native定位
        driver.context("NATIVE_APP");
        Thread.sleep(5000);
        //定位native
        //点击更多
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='更多']")).click();
        Thread.sleep(1000);
        //点击回到首页
        String cancelButton = "cmd /c start adb shell input tap 530,2250";
        Runtime.getRuntime().exec(cancelButton);
    }
}
