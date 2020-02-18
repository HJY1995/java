package jmeterJava;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class HelloThree extends AbstractJavaSamplerClient {
    String uname="";

    //定义GUI界面的参数。
    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("username", "Jerry");
        return params;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        //获取参数
        uname = context.getParameter("username");
    }

    //主体执行部分。
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        //new一个SampleResult对象，用来实现计时、结果回写等操作。
        SampleResult sr=new SampleResult();
        //初始化业务功能类。业务功能类就是真正用来实现请求发送的类。
        Request request=new Request();
        //业务操作
        try {
            //请求开始计时
            sr.sampleStart();
            //调用业务方法。
            String result=request.say(uname);
            //设置显示的请求数据，非必须。
            sr.setSamplerData("this is uname="+uname);
            //设置显示的响应数据，必须。
            sr.setResponseData(result,sr.TEXT);
            //设置请求的结束状态。
            sr.setSuccessful(true);
        } catch (Exception e) {
            sr.setResponseData("fail msg："+e.getMessage(),sr.TEXT);
            sr.setSuccessful(false);
        } finally {
            //请求结束计时。
            sr.sampleEnd();
        }
        return sr;
    }


}
