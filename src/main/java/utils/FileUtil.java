package utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
@Slf4j
public class FileUtil {

    public static String readCsv(String filePath) {
        StringBuilder sb = new StringBuilder();

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fr);

            String line = "";
            //组装读取结果
            while ((line = bf.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e.fillInStackTrace());
        } catch (IOException e) {
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String str = readCsv("data/param.json");
        JSONObject json = JSON.parseObject(str);
        System.out.println(json.getString("name"));
    }
}
