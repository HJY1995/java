import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    @Test
    public void httpGet() throws IOException {
        //创建client有两种途径，查看源码可知最终都是调用 HttpClientBuilder.create().build();
        CloseableHttpClient client= HttpClients.createDefault();
        //CloseableHttpClient client= HttpClients.custom().build();

        //创建 get 对象,
        HttpGet get=new HttpGet("http://www.httpbin.org/get");
        //设置 请求头
        get.setHeader("Referer","http://www.httpbin.org/");
        get.setHeader("accept","application/json");
        // HttpGet还可以设置connectTimeout,socketTimeout等，使用 get.setConfig();

        //获取响应
        CloseableHttpResponse response=client.execute(get);
        HttpEntity entity=response.getEntity();
        String result=EntityUtils.toString(entity);
        //关闭流,释放连接
        EntityUtils.consume(entity);
        response.close();

        //解析result，是json的话，可以使用 fastjson来解析
        JSONObject resultJson= JSON.parseObject(result);
        System.out.println(resultJson.getString("headers"));
    }

    @Test
    public void httpPost() throws IOException {
        //创建client
        CloseableHttpClient client=HttpClients.createDefault();

        //创建 HttpPost
        HttpPost post=new HttpPost("http://www.httpbin.org/post");
        //设置请求头
        post.setHeader("accept","application/json");
        post.setHeader("Referer","http://www.httpbin.org/");
        //设置请求参数
//        StringEntity se=new StringEntity("this is request param", ContentType.APPLICATION_JSON);
//        post.setEntity(se);
        NameValuePair nvp=new BasicNameValuePair("requestParam","this is request param");
        List<NameValuePair> nvps=new ArrayList<>();
        nvps.add(nvp);
        UrlEncodedFormEntity param=new UrlEncodedFormEntity(nvps, Charset.defaultCharset());
        post.setEntity(param);
        //获取响应
        CloseableHttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String result=EntityUtils.toString(entity);
        //关闭流和连接
        EntityUtils.consume(entity);
        response.close();
        //解析result
        JSONObject jsonObject=JSON.parseObject(result);
        System.out.println(jsonObject);

    }

    @Test
    public void  httpCookie(){
        //创建cookieStore存储cookie
        CookieStore cookieStore=new BasicCookieStore();
        //创建cookie对象
        Cookie cookie=new BasicClientCookie("BIDUPSID","9E9076941C4F683E0D1465223ECA3650");
        cookieStore.addCookie(cookie);




        CloseableHttpClient client1=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        CloseableHttpClient client2=HttpClients.custom().setDefaultCookieStore(cookieStore).build();








    }
}
