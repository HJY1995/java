package a_request_demo.provider;

import a_request_demo.utils.FIleUtil;
import org.testng.annotations.DataProvider;

public class DataPro {
    @DataProvider
    public static Object[][] data(){
        String str= FIleUtil.reader("src/test/data/data.txt");
        return new Object[][]{
                //字符串格式化处理参数
                {String.format(str,18)},
                {String.format(str,58)}
        };
    }
}
