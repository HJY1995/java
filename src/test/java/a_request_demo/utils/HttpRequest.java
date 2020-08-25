package a_request_demo.utils;

import a_request_demo.provider.DataPro;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class HttpRequest {
    //在每个测试方法之前执行
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest,do something...");
    }
    //使用@DataProvider提供的数据
    @Test(dataProvider = "data")
    public void test1(String param){
        System.out.println(param);
        Assert.assertEquals(param,"abc");
    }
    //标记一个数据提供的方法
    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"abc"},
                {"bcd"}
        };
    }
    @Test
    public void test2(){
        Assert.assertEquals("abcd","abcd");
    }
    //不执行此用例
    @Test(enabled = false)
    public void test3(){
        Assert.assertTrue(1>2);
    }
    //在每个测试方法之后执行
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest,do something...");
    }

    @Test(dataProviderClass = DataPro.class,dataProvider = "data")
    public void requestTest(String age) throws IOException {
        //创建client
        CloseableHttpClient client= HttpClients.createDefault();
        //创建 HttpPost
        HttpPost post=new HttpPost("http://www.httpbin.org/post");
        //设置请求参数
        StringEntity se=new StringEntity(age);
        post.setEntity(se);
        //获取响应
        CloseableHttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String result= EntityUtils.toString(entity);
        //关闭流和连接
        EntityUtils.consume(entity);
        response.close();
        //获取statusCode并断言
        int statusCode=response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200,"statusCode不是200");
        //获取age并断言
        JSONObject jsonObject=JSON.parseObject(result);
        JSONObject data= jsonObject.getJSONObject("data");
        String ActualAge=data.getString("age");
        Assert.assertEquals(ActualAge,"18","响应返回的age错误");
    }




}














