package com.testing.class8;

import appdriver.KeywordOfApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeituanTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        KeywordOfApp app = new KeywordOfApp();
        app.StartAppium("4723", "10");
        app.runMM("6.0.1", "127.0.0.1:7555",
                "com.tencent.mm", ".ui.LauncherUI",
                "http://127.0.0.1:4723/wd/hub", "10");
        app.appiumSwipe("300", "300", "300", "600");
        app.click("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]//android.widget.ImageView[@resource-id='com.tencent.mm:id/gd']");
        app.wait("20");
        app.printContexts();
        app.wait("5");
        app.click("//android.view.View[@content-desc=\"美食\"]");
        app.wait("5");
        //切换context到微信的H5webview
        app.switchContext("WEBVIEW_com.tencent.mm:appbrand0");
        log.info("切换context完成");
        app.click("/html/body/wx-view/wx-view/wx-view[2]/wx-wm-navigator[1]/wx-navigator/wx-view/wx-midas/wx-view/wx-view/wx-view/wx-view[2]/wx-view[1]/wx-view");
    }
}
