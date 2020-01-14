import com.alibaba.fastjson.JSON;
import org.apache.commons.text.StringEscapeUtils;
import utils.FileUtil;

/**
 * JSON等字符格式的转义和反转义
 */
public class EscapeTest {
    public static void main(String[] args) {
        String str = FileUtil.readCsv("E:\\IdeaProjects\\javaStudy\\src\\test\\data\\capability.json");
        System.out.println("读取的文件String:\n"+str);
        str= JSON.parseObject(str).toJSONString();
        System.out.println("转换为JSONString:\n"+str);
        //转义
        str= StringEscapeUtils.escapeJson(str);
        System.out.println("转义后的JSONString:\n"+str);
        //反转义
        str=StringEscapeUtils.unescapeJson(str);
        System.out.println("反转义后的JSONString:\n"+str);
    }
}
