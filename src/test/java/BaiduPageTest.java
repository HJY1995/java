import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import page.BaiduPage;
import webui.KeywordOfWeb;

public class BaiduPageTest {
    public KeywordOfWeb kw = new KeywordOfWeb();

    @Test
    public void baiduSerch() {
        kw.openBrowser("chrome");
        BaiduPage baiduPage = new BaiduPage(kw);
        baiduPage.load();
        baiduPage.search();
    }


    @Test
    public void a() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
    }

    public static void main(String[] args) {

    }
}
