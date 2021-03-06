package appdriver;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

@Slf4j
public class AppDriver {
    private AndroidDriver driver;

    // 设备名称、app的main Activity类、appium服务器ip端口、等待启动时间
    public AppDriver(String platformVersion, String deviceName, String appPackage, String appActivity, String appiumServerIP, String waitTime) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //必要参数
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("platformName", "Android");
        //可选参数
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        //电脑连接了多个设备的时候，指定设备。
        capabilities.setCapability("udid", deviceName);

        try {
            driver = new AndroidDriver(new URL(appiumServerIP), capabilities);
            int t = Integer.parseInt(waitTime);
            Thread.sleep(t * 1000);
            log.info("APP正在启动中……");
        } catch (Exception e) {
            log.error("APP启动失败，请检查配置！");
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }

    public AndroidDriver getdriver() {
        return this.driver;
    }
}
