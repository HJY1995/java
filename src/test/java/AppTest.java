import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest {
    //使用appium-server启动APP
    public static void main(String[] args) throws IOException, InterruptedException {
        //启动appium-server
        String startAppium="cmd /c start appium -a 127.0.0.1 -p 4723 -g E:\\appium.log --local-timezone --log-timestamp";
        Runtime.getRuntime().exec(startAppium);
        //启动appium-server需要一定时间，这里需要设置等待时间
        Thread.sleep(5000);

        //设置Capabilities参数
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        //必要参数
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion","6.0.1");
        desiredCapabilities.setCapability("appPackage","com.tencent.mobileqq");
        desiredCapabilities.setCapability("appActivity",".activity.LoginActivity");
        //可选参数
        //不要重置签名
        desiredCapabilities.setCapability("noSign",true);
        //不清除缓存
        desiredCapabilities.setCapability("noReset",true);
        //设置使用unicode键盘，让自动化脚本实现过程中能够输入中文，成对使用，单独使用下面的某一个属性会ignore
        desiredCapabilities.setCapability("unicodeKeyboard",true);
        desiredCapabilities.setCapability("resetKeyboard",true);

        //电脑连接了多个设备时，指定设备
        //desiredCapabilities.setCapability("udid","127.0.0.1:7555");

        //创建driver对象
        AndroidDriver  driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(3000);
        //定位并操作元素
        driver.findElement(MobileBy.AccessibilityId("请输入QQ号码或手机或邮箱")).sendKeys("123456");
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc='密码 安全']")).sendKeys("132456");
        driver.findElement(By.id("com.tencent.mobileqq:id/login")).click();

        Thread.sleep(5000);
        //关闭APP和driver
        driver.closeApp();
        driver.quit();

        //关闭appium-server 和命令行窗口
        String killCmd="taskkill /F /IM cmd.exe";
        String killNode="taskkill /F /IM node.exe";

        Runtime.getRuntime().exec(killNode);
        Runtime.getRuntime().exec(killCmd);
    }

    //使用appium-desktop启动APP
    @Test
    public void appConnect() throws InterruptedException, MalformedURLException {
        //配置Capabilities参数
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //设置必须的参数
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "6.0.1");
        desiredCapabilities.setCapability("appPackage", "com.tencent.mobileqq");
        desiredCapabilities.setCapability("appActivity", ".activity.LoginActivity");
        //以下为可选参数
        //不要重置签名
        desiredCapabilities.setCapability("noSign", true);
        //不清除缓存
        desiredCapabilities.setCapability("noReset", true);
        //设置使用unicode键盘，让自动化脚本实现过程中能够输入中文，成对使用，单独使用下面的某一个属性会ignore
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);
        //电脑连接了多个设备时，需要指定设备
        //desiredCapabilities.setCapability("udid","127.0.0.1:7555");

        //创建driver对象，URL参数是固定写法，可参考appium-desktop的custom server的Remote Host，Remote Por，Remote Path
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        //启动APP需要一定的时间，注意设置等待时间
        Thread.sleep(3000);
        //使用上面提到过的三种元素定位方式
        driver.findElement(MobileBy.AccessibilityId("请输入QQ号码或手机或邮箱")).sendKeys("123456");
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc='密码 安全']")).sendKeys("12456");
        driver.findElement(By.id("com.tencent.mobileqq:id/login")).click();

        Thread.sleep(10000);
        //关闭APP和driver
        driver.closeApp();
        driver.quit();
    }

    //启动和关闭appium-server
    @Test
    public void startAndKillAppium() throws IOException, InterruptedException {
        //启动appium-server
        String startAppium="cmd /c start appium -a 127.0.0.1 -p 4723 -g E:\\appium.log --local-timezone --log-timestamp";
        //Runtime.getRuntime().exec() 执行cmd命令
        Runtime.getRuntime().exec(startAppium);

        Thread.sleep(2000);
        //关闭appium-server和命令行窗口，因为appium-server是用nodejs写的，可以直接关闭node就关闭了appium-server
        String KillNode="taskkill /F /IM node.exe";
        String killCmd="taskkill /F /IM cmd.exe";
        Runtime.getRuntime().exec(KillNode);
        Runtime.getRuntime().exec(killCmd);
    }

    //appium常用方法
    @Test
    public void useAdb() throws InterruptedException, IOException {
        //配置Capabilities参数
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //设置必须的参数
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "6.0.1");
        desiredCapabilities.setCapability("appPackage", "com.baidu.tieba");
        desiredCapabilities.setCapability("appActivity", ".activity.LoginActivity");
        //以下为可选参数
        //不要重置签名
        desiredCapabilities.setCapability("noSign", true);
        //不清除缓存
        desiredCapabilities.setCapability("noReset", true);
        //设置使用unicode键盘，让自动化脚本实现过程中能够输入中文，成对使用，单独使用下面的某一个属性会ignore
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);
        //电脑连接了多个设备时，需要指定设备
        //desiredCapabilities.setCapability("udid","127.0.0.1:7555");

        //创建driver对象，URL参数是固定写法，可参考appium-desktop的custom server的Remote Host，Remote Por，Remote Path
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        //启动APP需要一定的时间，注意设置等待时间
        Thread.sleep(3000);

        //调用adb滑动屏幕
//        String cmd="cmd /c start adb shell input swipe 400,800,400,400,TIME ";
//        Runtime.getRuntime().exec(cmd);

        //使用appium的方法，使用坐标进行滑动屏幕
//        TouchAction action = new TouchAction(driver);
//        //设置起点和终点
//        PointOption pressPoint=PointOption.point(x, y);
//        PointOption movePoint=PointOption.point(x1, y1);
//        //滑动操作由长按起点->移动到终点->松开组成。
//        action.longPress(pressPoint).moveTo(movePoint).release().perform();

        //用appium的方法，使用坐标点击
//        TouchAction action = new TouchAction(driver);
//        PointOption pressPoint=PointOption.point(x, y);
//        // action类分解动作，先长按，再移动到指定位置，再松开
//        action.tap(pressPoint).release().perform();


        //用appium的方法，长按某个坐标
//        TouchAction action = new TouchAction(driver);
//        PointOption pressPoint=PointOption.point(x, y);
//        //设置长按的时间
//        Duration last = Duration.ofSeconds(t);
//        WaitOptions wait= WaitOptions.waitOptions(last);
//        //长按坐标
//        action.longPress(pressPoint).waitAction(wait).perform();

        //使用appium的方法，长按某个元素
//        Duration last = Duration.ofSeconds(t);
//        TouchAction action = new TouchAction(driver);
//        //定位到一个元素，并且在该元素上长按指定的时长
//        action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(driver.findElementByXPath(xpath))).withDuration(last)).waitAction(WaitOptions.waitOptions(last)).perform();






        Thread.sleep(10000);
        //关闭APP和driver
        driver.closeApp();
        driver.quit();
    }




}
