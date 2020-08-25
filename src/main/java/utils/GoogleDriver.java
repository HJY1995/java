package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleDriver {
    WebDriver driver;

    public GoogleDriver(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions chromeOptions = new ChromeOptions();
        //去除浏览器上的爬虫信息"Chrome正在受到自动测试软件的控制"
        //这是老版本的 Chromedriver，新版Chromedriver不再有这个属性
        //chromeOptions.addArguments("disable-infobars");
        //新版Chromedriver设置
        //chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        /**
         * 加载Chrome用户文件,主要是Chrome的cookie,缓存，让浏览器内容加载更快一些，Chrome访问chrome://version/可以拿到路径
         * 在运行前先关闭chrome，因为selenium下打开浏览器和正常打开浏览器会有占用配置文件的冲突
         * 其他chromeOptions配置百度
         */
        //chromeOptions.addArguments("--user-data-dir=C:\\Users\\HJY\\AppData\\Local\\Google\\Chrome\\User Data");

        //最大化浏览器窗口
        chromeOptions.addArguments("--start-maximized");

        //隐藏浏览器
        //chromeOptions.addArguments("--headless");

        try {
            this.driver = new ChromeDriver(chromeOptions);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("log--error: 创建driver失败");
        }
    }

    public void quit() {
        this.driver.quit();
    }

    public void close() {
        this.driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
