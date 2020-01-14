package page;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webui.KeywordOfWeb;

public class BaiduPage {
    public KeywordOfWeb kw;
    public String url="http://www.baidu.com";
	//声明所有页面中用到的元素，作为类中的变量。
	//将@FindBy注解通过对应的定位方法找到的元素赋值给成员变量
    @FindBy(xpath = "//input[@id='kw']")
    public WebElement  input;

    @FindBy(xpath = "//input[@id='su']")
    public WebElement  submit;

    public BaiduPage(KeywordOfWeb kw){
        this.kw=kw;
        //使用selenium的pageFactory，完成元素的初始化
        PageFactory.initElements(kw.driver,this);
    }

    public void load(){

        kw.visitWeb(url);
    }

    public void search(){
        input.sendKeys("12306");
        submit.click();
        Assert.assertTrue(kw.getTitle().contains("百度"),"断言失败");
    }
}
