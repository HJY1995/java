package com.testing.class8;

import appdriver.KeywordOfApp;

import java.util.Set;


public class QzoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp app =new KeywordOfApp();
		app.StartAppium("12345", "15");
		app.runBrowser("6.0.1", "127.0.0.1:7555", "http://127.0.0.1:12345/wd/hub", "5");
		//隐式等待
		app.implicitlyWait();
		app.driver.get("https://ui.ptlogin2.qq.com/cgi-bin/login?pt_hide_ad=1&style=9&appid=549000929&pt_no_auth=1&pt_wxtest=1&daid=5&s_url=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex");
		app.wait("5");
		app.input("//input[@id='u']","2798145476");
		app.wait("5");
		//获取页面中的上下文层级
		Set<String> contexts= app.driver.getContextHandles();
		for(String s:contexts) {
			System.out.println(s);
		}
		//切换到NATIVE层
		app.wait("3");
		app.driver.context("NATIVE_APP");
		app.input("//android.webkit.WebView[@content-desc=\"手机统一登录\"]/android.widget.ListView/android.view.View[2]/android.widget.EditText", "roy12345");
		//切换到CHROMIUM，webview层
		app.wait("3");
		app.driver.context("CHROMIUM");
		app.click("//div[@id='go']");
	}

}
