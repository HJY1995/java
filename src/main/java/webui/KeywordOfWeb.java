package webui;

import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AutoLog;
import utils.GoogleDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class KeywordOfWeb {
    public WebDriver driver;

    /**
     * 打开浏览器
     * @param browserType
     */
    public void openBrowser(String browserType){
        if(browserType.equalsIgnoreCase("chrome")){
            try {
                GoogleDriver googleDriver=new GoogleDriver("E:\\chromedriver_win32\\chromedriver.exe");
                driver = googleDriver.getDriver();
                AutoLog.log.info("打开Chrome");
                invisibleWait();
            } catch (Exception e) {
             //  e.printStackTrace();
                AutoLog.log.error(e,e.fillInStackTrace());
            }
        }
    }

    public void visitWeb(String url){
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
//            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 先清空，再输入内容
     * @param name
     * @param content
     */
    public void inputByName(String name,String content){
        try {
            WebElement element=driver.findElement(By.name(name));
            element.clear();
            element.sendKeys(content);
        } catch (Exception e) {
           // e.printStackTrace();
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    public void input(String xpath,String content){
        try {
            WebElement element=driver.findElement(By.xpath(xpath));
            element.clear();
            element.sendKeys(content);
        } catch (Exception e) {
           // e.printStackTrace();
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    public void click(String xpath){
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
           // e.printStackTrace();
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    public String getTitle(){
        try {
            String title=driver.getTitle();
            System.out.println(title);
            return title;

        } catch (Exception e) {
            AutoLog.log.error(e,e.fillInStackTrace());
        }
        return null;
    }

    /**
     * 显示等待某个元素 10S ，直到这个元素在页面加载完成
     * @param xpath
     */
    public void visibleWait(final String xpath){
        //定义最长等待时间为 10s
        WebDriverWait driverWait=new WebDriverWait(driver,10);
        //方式 1
        driverWait.until(new ExpectedCondition<WebElement>() {
                                     @NullableDecl
                                     public WebElement apply(@NullableDecl WebDriver d) {
                                         return d.findElement(By.xpath(xpath));
                                     }
                                 });
        //方式 2
       //driverWait.until(ExpectedConditions.attributeContains());
    }

    /**
     * 隐式等待所有元素在页面加载完成,最长等待时间为 10S
     */
    public void invisibleWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * 强制等待，单位是 秒
     * @param waitTime
     */
    public void sleep(String waitTime){
        try {
            int time=  Integer.parseInt(waitTime);
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            AutoLog.log.error(e,e.fillInStackTrace());
        }

    }

    public void closeBrowser(){
        try {
            driver.quit();
        } catch (Exception e) {
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 打印一组元素的文本内容
     * @param xpath
     */
    public void printElementsText(String xpath){
        try {
            List<WebElement> elements=driver.findElements(By.xpath(xpath));
            for(WebElement e:elements){
                System.out.println(e.getText());
            }
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }

    }

    /**
     * 鼠标悬停在某个元素上
     * @param xpath
     */
    public void hover(String xpath){
        try {
            WebElement element=driver.findElement(By.xpath(xpath));
            Actions actions=new Actions(driver);
            actions.moveToElement(element).perform();
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * driver切换到 iframe
     * @param xpath
     */
    public void intoIframe(String xpath){
        try {
            WebElement iframe=driver.findElement(By.xpath(xpath));
            driver.switchTo().frame(iframe);
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
            saveScreenShot("切换到iframe");
        }
    }

    /**
     * 跳出 iframe,切回主页面
     */
    public void outIframe(){
        try {
            driver.switchTo().defaultContent();
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 通过标题切换窗口,不关闭原窗口
     * @param targetTitle
     */
    public void switchWindowByTitle(String targetTitle){
        Set<String> handles=driver.getWindowHandles();
        try {
            for(String h:handles){
                if(driver.switchTo().window(h).getTitle().equalsIgnoreCase(targetTitle)){
                    driver=driver.switchTo().window(h);
                }
            }
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * driver 切换到新窗口，并关闭原窗口,仅限两个窗口的时候
     */
    public void closeOldWindow(){
        //获取当前句柄
        String presentHandle=driver.getWindowHandle();
        //获取所有句柄
        Set<String> handles=driver.getWindowHandles();
        for(String h:handles){
            if(!h.equalsIgnoreCase(presentHandle)){
                try {
                   driver= driver.switchTo().window(h);
                }catch (Exception e){
                    AutoLog.log.error(e,e.fillInStackTrace());
                    saveScreenShot("切换到新窗口");
                }
            }
        }
    }

    /**
     * 使用下拉框的文本内容选择下拉框
     * @param selectXapth
     * @param text
     */
    public void selectByText(String selectXapth,String text){
        try {
            WebElement element=driver.findElement(By.xpath(selectXapth));
            Select select=new Select(element);
            select.selectByVisibleText(text);
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 使用下拉框的索引选择下拉框
     * @param selectXapth
     * @param index
     */
    public void selectByIndex(String selectXapth,String index){
        try {
            WebElement element=driver.findElement(By.xpath(selectXapth));
            Select select=new Select(element);
            select.selectByIndex(Integer.parseInt(index));
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
            saveScreenShot("通过索引选择下拉框");
        }
    }

    /**
     * 上传文件
     * @param xpath
     * @param filePath
     */
    public void uploadFiles(String xpath,String filePath){
        try {
            driver.findElement(By.xpath(xpath)).sendKeys(filePath);
        }catch (Exception e){
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 截图
     * @param method
     */
    public void saveScreenShot(String method){
        //组装文件名
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sdf.format(new Date());
        String screenShotName="log/scrShoot/"+ method +" "+date+".png";
        //新建文件
        File scrShoot=new File(screenShotName);
        //截图
        File tmp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            //将截图的临时文件拷贝到 新建的scrShoot
            FileUtils.copyFile(tmp,scrShoot);
        } catch (IOException e) {
            AutoLog.log.error(e,e.fillInStackTrace());
        }
    }

    /**
     * 获取cookies
     * @return
     */
    public Set<Cookie> getCookie(){
        Set<Cookie> cookies=driver.manage().getCookies();
        for(Cookie c:cookies){
            System.out.println(c.getName()+"="+c.getValue());
        }
        return cookies;
    }

}
