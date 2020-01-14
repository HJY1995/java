import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    private String url="http://www.baidu.com";
    //声明所有页面中用到的元素，作为类中的变量。
    //将@FindBy注解通过对应的定位方法找到的元素赋值给成员变量
    @FindBy(xpath = "//input[@id='kw']")
    public WebElement input;

    @FindBy(xpath = "//input[@id='su']")
    public WebElement  submit;

    public PageObject(WebDriver driver){
        initPage(driver);
    }

    private void initPage(WebDriver driver){
        //打开网页
        driver.get(url);
        //使用selenium的pageFactory，完成元素的初始化
        PageFactory.initElements(driver,this);
    }
    //使用搜索
    public void search(){
        input.sendKeys("12306");
        submit.click();
    }

}
